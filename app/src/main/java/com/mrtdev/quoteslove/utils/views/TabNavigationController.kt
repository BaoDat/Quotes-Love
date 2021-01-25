package com.mrtdev.quoteslove.utils.views

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.databinding.NullableBooleanConversion

class  TabNavigationController(
    val context: Context,
    @IdRes val containerId: Int,
    @IdRes val appStartDestinationId: Int
//    val navigateUpOnTabChanged: () -> Unit?
) {
    private val backStack = BackStack.of(appStartDestinationId)
    lateinit var activity: Activity
    lateinit var fragmentManager: FragmentManager
    private var listener: OnNavigationItemChanged? = null
    private var navGraphProvider: NavGraphProvider? = null

    interface OnNavigationItemChanged {
        fun onItemChanged(itemId: Int)
    }

    interface NavGraphProvider {
        @NavigationRes
        fun getNavGraphId(itemId: Int): Int
    }

    init {
        var ctx = context
        while (ctx is ContextWrapper) {
            if (ctx is Activity) {
                activity = ctx
                fragmentManager = (activity as FragmentActivity).supportFragmentManager
                break
            }
            ctx = ctx.baseContext
        }

        fragmentManager.addOnBackStackChangedListener {
//            navigateUpOnTabChanged()
        }
    }

    fun getTabNavController() : NavController = activity.findNavController(containerId)

    fun getCurrentDestination(): NavDestination? =
        getTabNavController().currentDestination

    fun setOnItemNavigationChanged(listener: (itemId: Int) -> Unit) {
        this.listener = object : OnNavigationItemChanged {
            override fun onItemChanged(itemId: Int) {
                listener.invoke(itemId)
            }
        }
    }

    fun setNavGraphProvider(provider: NavGraphProvider) {
        navGraphProvider = provider
    }

    fun onNavigationItemReselected(item: MenuItem) {
        // If the user press a second time the navigation button, we pop the back stack to the root
        getTabNavController().popBackStack(item.itemId, false)
    }

    fun onNavigationItemSelected(itemId: Int = backStack.last()): Boolean {
        // Replace fragment representing a navigation item
        val fragment = fragmentManager.findFragmentByTag(itemId.toString())
            ?: NavHostFragment.create(navGraphProvider?.getNavGraphId(itemId)
                ?: throw IllegalStateException("You need to set up a NavGraphProvider with " +
                        "TabNavigationController#setNavGraphProvider")
            )
        if (!activity.isFinishing) {
            fragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    R.anim.nav_default_enter_anim,
                    R.anim.nav_default_exit_anim
                )
                replace(containerId, fragment, itemId.toString())
                addToBackStack(null)
                // Attempt use commitAllowingStateLoss() instead of commit() to prevent Can not perform this action after onSaveInstanceState
                commitAllowingStateLoss()
            }

            // Add to back stack
            backStack.moveLast(itemId)

            listener?.onItemChanged(itemId)
        }

        return true
    }

    fun getItemChildOnTop(): Fragment? = fragmentManager.findFragmentById(containerId)?.childFragmentManager?.fragments?.last()

    fun onBackPressed() {
        val poppedChild = fragmentManager.findFragmentById(containerId)?.childFragmentManager?.popBackStackImmediate()
        // If item contains child fragment in its back stack, so only back to previous item in current item's stack.
        if (NullableBooleanConversion.safeUnbox(poppedChild)) {
            Navigation.findNavController(activity, containerId).popBackStack()
            return
        }
        when {
            // If the stack has only one and it's not the navigation home we should
            // ensure that the application always leave from startDestination
            backStack.last() != appStartDestinationId -> {
                backStack.clear()
                backStack.add(0, appStartDestinationId)
                onNavigationItemSelected()
            }
            // Navigation stack is empty, so finish the activity
            else -> activity.finish()
        }
    }

    private class BackStack : ArrayList<Int>() {
        companion object {
            fun of(vararg elements: Int): BackStack {
                val stack = BackStack()
                stack.addAll(elements.toTypedArray())
                return stack
            }
        }

        fun removeLast() = removeAt(size - 1)

        fun moveLast(item: Int) {
            remove(item)
            add(item)
        }
    }
}

// Convenience extension to set up the navigation
fun BottomNavigationView.setUpNavigation(tabNavigationController: TabNavigationController,
                                         onReselect: ((menuItem: MenuItem) -> Unit)? = null) {
    setOnNavigationItemSelectedListener {
        tabNavigationController.onNavigationItemSelected(it.itemId)
    }
    setOnNavigationItemReselectedListener {
        tabNavigationController.onNavigationItemReselected(it)
        onReselect?.invoke(it)
    }
    tabNavigationController.setOnItemNavigationChanged { itemId ->
        menu.findItem(itemId).isChecked = true
    }
}