package com.mrtdev.quoteslove.ui.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mrtdev.quoteslove.ui.main.navigator.MainNavigator
import com.mrtdev.quoteslove.ui.main.viewmodel.MainViewModel
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseBindingActivity
import com.mrtdev.quoteslove.databinding.ActivityMainBinding
import com.mrtdev.quoteslove.utils.setViewForScreen
import com.mrtdev.quoteslove.utils.toggleFullScreen
import javax.inject.Inject


class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>() {

    companion object {

        fun prepareIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    override val layoutId = R.layout.activity_main
    override val viewModelClass = MainViewModel::class

    @Inject
    lateinit var navigator: MainNavigator

    override fun init(savedInstanceState: Bundle?) {
        viewModel.navigation bindTo navigator::navigate
        toggleFullScreen(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun onResume() {
        setViewForScreen()
        supportActionBar?.show()
        setActionBarTitle(resources.getString(R.string.title_home))
        super.onResume()
    }
}
