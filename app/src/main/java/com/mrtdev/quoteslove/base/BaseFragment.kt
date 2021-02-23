package com.mrtdev.quoteslove.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.mrtdev.quoteslove.BR
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.domain.base.exceptions.ConnectivityErrorSource
import com.mrtdev.quoteslove.domain.base.exceptions.QuotesLoveException
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import com.mrtdev.quoteslove.utils.plusAssign
import kotlin.reflect.KClass

abstract class BaseFragment<TBinding : ViewDataBinding, TViewModel : ViewModel> : Fragment(), HasAndroidInjector {

    protected var fragmentListener: OnFragmentListener? = null

    open fun fragmentTag(): String = this.javaClass.simpleName

    @Inject
    lateinit var factory: ViewModelsFactory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    lateinit var viewModel: TViewModel
    lateinit var binding: TBinding
    protected val compositeDisposable = CompositeDisposable()

    protected abstract val layoutId: Int
    protected abstract val viewModelClass: KClass<TViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)[viewModelClass.java]

        init(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.model, viewModel)
        binding.lifecycleOwner = this
        initView(savedInstanceState)

        return binding.root
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector

    protected fun handleError(error: QuotesLoveException) {
        when (error) {
            is QuotesLoveException.NoConnectivityError -> showNoInternet(error.source)
            else -> showUnknownError(error)
        }
    }

    protected open fun showNoInternet(source: ConnectivityErrorSource) {
        when (source) {
            ConnectivityErrorSource.MOBILE_DEVICE -> R.string.no_connectivity_error
            ConnectivityErrorSource.SERVER -> R.string.no_connectivity_error
        }.let {
        }
    }

    private fun setActionBarTitle(@StringRes resId: Int) {
        setActionBarTitle(getString(resId))
    }

    private fun setActionBarTitle(title: String) {
        (activity as BaseActivity).setActionBarTitle(title)
    }

    fun setupToolBar(@StringRes resId: Int){
        (activity as? AppCompatActivity)?.run {
            supportActionBar?.show()
            setActionBarTitle(resId)
        }
    }

    /**
    //     * Function change between fragment another
    //     */

//
//    open fun onBackPressed() {
//        fragmentListener?.onFragmentBackPressed()
//    }
//
//    fun isFragmentInstanceInBackStack(fragment: Class<out Fragment>): Boolean {
//        return (activity as BaseActivity).isFragmentInstanceInBackStack(fragment)
//    }
//
//    open fun changeFragment(baseFragment: Fragment) {
//        (activity as BaseActivity).changeFragment(baseFragment)
//    }
//
//    protected fun setRootFragment(baseFragment: Fragment) {
//        (activity as BaseActivity).setRootFragment(baseFragment)
//    }

    protected open fun showUnknownError(error: Throwable) {
    }

    protected inline infix fun <T> LiveData<T>.bindTo(crossinline action: (T?) -> Unit) =
        this.observe(this@BaseFragment, Observer { action(it) })

    protected inline infix fun <T> Observable<T>.bindTo(crossinline action: (T) -> Unit?) {
        compositeDisposable += observeOn(AndroidSchedulers.mainThread()).subscribe { action(it) }
    }

    protected inline infix fun Observable<Unit>.bindTo(crossinline action: () -> Unit?) {
        compositeDisposable += observeOn(AndroidSchedulers.mainThread()).subscribe { action() }
    }

    interface OnFragmentListener {
        fun onFragmentBackPressed()
    }
}