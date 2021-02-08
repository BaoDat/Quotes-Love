package com.mrtdev.quoteslove.ui.home.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentAllQuotesBinding
import com.mrtdev.quoteslove.ui.allquotes.view.AllQuotesFragment
import com.mrtdev.quoteslove.ui.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentAllQuotesBinding, HomeViewModel>() {

    companion object {
        fun newInstance(): AllQuotesFragment {
            return AllQuotesFragment()
        }
    }

    override val layoutId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun onResume() {
        super.onResume()
        setupToolBar(R.string.action_tile_home)
    }
}