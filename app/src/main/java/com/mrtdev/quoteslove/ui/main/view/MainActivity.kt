package com.mrtdev.quoteslove.ui.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.mrtdev.quoteslove.ui.main.navigator.MainNavigator
import com.mrtdev.quoteslove.ui.main.viewmodel.MainViewModel
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseBindingActivity
import com.mrtdev.quoteslove.databinding.ActivityMainBinding
import com.mrtdev.quoteslove.utils.views.TabNavigationController
import com.mrtdev.quoteslove.utils.views.setUpNavigation
import javax.inject.Inject
import org.parceler.Parcels


class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>(), TabNavigationController.NavGraphProvider {

    companion object {
        private const val EXTRA_TAB_ITEM = "tabItem"
        private const val EXTRA_LAST_SELECTED_TAB_ITEM = "lastSelectedTabItem"

        fun prepareIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    override val layoutId = R.layout.activity_main
    override val viewModelClass = MainViewModel::class

    @Inject
    lateinit var navigator: MainNavigator

    private val bottomNavController by lazy(LazyThreadSafetyMode.NONE) {
        TabNavigationController(this, R.id.container, R.id.action_home)
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.navigation bindTo navigator::navigate
//        toggleFullScreen(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
        bottomNavController.setNavGraphProvider(this)

        binding.tabbar.setUpNavigation(bottomNavController)
        if (savedInstanceState == null) {
            bottomNavController.onNavigationItemSelected()
        } else {
            val bundle = savedInstanceState.getBundle(EXTRA_LAST_SELECTED_TAB_ITEM)
            bundle?.let {
                val tabItem = Parcels.unwrap<MainNavigator.TabItem>(bundle.getParcelable<Parcelable>(EXTRA_TAB_ITEM))
                showTabItem(tabItem)
            }
        }
    }

    private fun showTabItem(item: MainNavigator.TabItem) {
        when (item) {
            MainNavigator.TabItem.ALL -> {
                bottomNavController.onNavigationItemSelected(R.id.action_all)
            }
            MainNavigator.TabItem.TYPE -> {
                bottomNavController.onNavigationItemSelected(R.id.action_type)
            }
            MainNavigator.TabItem.HOME -> {
                bottomNavController.onNavigationItemSelected(R.id.action_home)
            }
            MainNavigator.TabItem.LIKE -> {
                supportActionBar?.hide()
                bottomNavController.onNavigationItemSelected(R.id.action_like)
            }
            MainNavigator.TabItem.INFO -> {
                bottomNavController.onNavigationItemSelected(R.id.action_info)
            }
        }
    }

    override fun getNavGraphId(itemId: Int): Int {
        return when (itemId) {
            R.id.action_all -> R.navigation.nav_tab_all
            R.id.action_type -> R.navigation.nav_tab_type
            R.id.action_home -> R.navigation.nav_tab_home
            R.id.action_like -> R.navigation.nav_tab_like
            R.id.action_info -> R.navigation.nav_tab_info
            else -> R.navigation.nav_tab_home
        }
    }
}
