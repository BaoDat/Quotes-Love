package com.mrtdev.quoteslove.ui.love.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseActivity
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentListBinding
import com.mrtdev.quoteslove.ui.love.viewmodel.ListViewModel

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override val layoutId = R.layout.fragment_list
    override val viewModelClass = ListViewModel::class

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).apply {
            (activity as? BaseActivity)?.supportActionBar?.show()
        }
//        setActionBarTitle("Danh sách yêu thích")
    }
}