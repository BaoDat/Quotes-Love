package com.mrtdev.quoteslove.ui.allquotes.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseActivity
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentAllQuotesBinding
import com.mrtdev.quoteslove.ui.allquotes.viewmodel.AllQuotesViewModel

class AllQuotesFragment : BaseFragment<FragmentAllQuotesBinding, AllQuotesViewModel>() {

    companion object {
        fun newInstance(): AllQuotesFragment {
            return AllQuotesFragment()
        }
    }

    override val layoutId = R.layout.fragment_all_quotes
    override val viewModelClass = AllQuotesViewModel::class

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).apply {
            (activity as? BaseActivity)?.supportActionBar?.show()
        }
        setActionBarTitle("Tất cả thính")
    }
}