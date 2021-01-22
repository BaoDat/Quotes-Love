package com.mrtdev.quoteslove.base

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.ui.callbacks.ProgressCallback
import com.mrtdev.quoteslove.utils.showProgressDialog
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), ProgressCallback, HasAndroidInjector,
    BaseFragment.OnFragmentListener {

    private var currentFragmentTag: String? = null

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    protected lateinit var mProgressDialog: Dialog

    protected var compositeDisposable = CompositeDisposable()

    companion object {
        private const val ARG_CURRENT_FRAGMENT_TAG = "ARG_CURRENT_fragment_tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        currentFragmentTag = savedInstanceState?.getString(ARG_CURRENT_FRAGMENT_TAG)
        mProgressDialog = showProgressDialog(this)
    }

    fun setActionBarTitle(@StringRes resID: Int) {
        // supportActionBar?.title = title
        setActionBarTitle(getString(resID))
    }

    open fun setActionBarTitle(title: String) {
//        supportActionBar?.title = ""
//         supportActionBar?.title = title
         findViewById<TextView>(R.id.txt_title)?.text = title
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(ARG_CURRENT_FRAGMENT_TAG, currentFragmentTag)
//    }
//
    override fun onFragmentBackPressed() {
        onBackPressed()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
    /**
    //     * Function add fragment into Activity
    //     */
//
//    /**
//     * Remove top fragment in stack and add new one.
//     */
//    fun replaceFragmentAtTopStack(newFragment: Fragment) {
//        if (currentFragmentTag == newFragment.javaClass.simpleName) {
//            return
//        }
//        supportFragmentManager.popBackStack()
//        changeFragment(newFragment)
//    }
//
//    fun changeFragment(newFragment: Fragment) {
//        changeFragment(newFragment, true)
//    }
//
//    open fun changeFragment(newFragment: Fragment, isDuplicateAllowed: Boolean) {
//        if (!isDuplicateAllowed && currentFragmentTag == newFragment.javaClass.simpleName) {
//            return
//        }
//        currentFragmentTag = newFragment.javaClass.simpleName
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, newFragment, currentFragmentTag)
//            .addToBackStack(currentFragmentTag)
//            .commit()
//    }
//
//    fun getFragmentOnTop(): Fragment? {
//        return supportFragmentManager.findFragmentById(R.id.fragment_container)
//    }
//
//    fun isFragmentOnTop(fragment: Class<out Fragment>): Boolean {
//        return currentFragmentTag == fragment.simpleName
//    }
//
//    fun isFragmentInstanceInBackStack(fragment: Class<out Fragment>): Boolean {
//        return supportFragmentManager.findFragmentByTag(fragment.simpleName) != null
//    }
//
//    /**
//     * Clear all fragment in stack and add [newFragment] to stack.
//     */
//    fun setRootFragment(newFragment: Fragment) {
//        currentFragmentTag = newFragment.javaClass.simpleName
//        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, newFragment, currentFragmentTag)
//            .addToBackStack(currentFragmentTag)
//            .commit()
//    }

    override fun showProgress() {

    }

    override fun showProgressError() {
    }

    override fun showProgressSuccess() {
    }

    override fun hideProgress() {
    }
}