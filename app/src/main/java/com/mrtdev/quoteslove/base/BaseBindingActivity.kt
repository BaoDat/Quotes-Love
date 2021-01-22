package com.mrtdev.quoteslove.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mrtdev.quoteslove.BR
import com.mrtdev.quoteslove.R
import androidx.lifecycle.*
import com.mrtdev.quoteslove.base.exceptions.ConnectivityErrorSource
import com.mrtdev.quoteslove.base.exceptions.QuotesLoveException
import com.mrtdev.quoteslove.utils.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseBindingActivity<TBinding : ViewDataBinding, TViewModel : ViewModel> :
    BaseActivity() {

    @Inject
    lateinit var factory: ViewModelsFactory

    lateinit var viewModel: TViewModel
    lateinit var binding: TBinding

    protected abstract val layoutId: Int
    protected abstract val viewModelClass: KClass<TViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)[viewModelClass.java]

        init(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.model, viewModel)
        binding.lifecycleOwner = this

        initView(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)
    abstract fun initView(savedInstanceState: Bundle?)

    fun handleError(error: QuotesLoveException) {
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
            mProgressDialog.dismiss()
        }
    }

    protected open fun showUnknownError(error: Throwable) {
    }

    protected inline infix fun <T> LiveData<T>.bindTo(crossinline action: (T?) -> Unit) =
        this.observe(this@BaseBindingActivity, Observer { action(it) })

    protected inline infix fun <T> Observable<T>.bindTo(crossinline action: (T) -> Unit) {
        compositeDisposable += observeOn(AndroidSchedulers.mainThread()).subscribe { action(it) }
    }

    protected inline infix fun Observable<Unit>.bindTo(crossinline action: () -> Unit) {
        compositeDisposable += observeOn(AndroidSchedulers.mainThread()).subscribe { action() }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}