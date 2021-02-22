package com.mrtdev.quoteslove.ui.allquotes.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentAllQuotesBinding
import com.mrtdev.quoteslove.ui.allquotes.view.adapter.QuotesAdapter
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
        binding.rvQuotes.adapter =
            QuotesAdapter(
                requireContext(),
                this,
                viewModel.quotes
            )
//        binding.rvQuotes.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        setupToolBar(R.string.action_tile_all_quotes)
    }
}