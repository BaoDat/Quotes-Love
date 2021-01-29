package com.mrtdev.quoteslove.ui.info.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentInfoBinding
import com.mrtdev.quoteslove.ui.info.viewmodel.InfoViewModel

class InfoFragment : BaseFragment<FragmentInfoBinding, InfoViewModel>() {

    companion object {
        fun newInstance(): InfoFragment {
            return InfoFragment()
        }
    }

    override val layoutId = R.layout.fragment_info
    override val viewModelClass = InfoViewModel::class

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        setupToolBar(R.string.action_tile_info)
    }
}