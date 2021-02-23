package com.mrtdev.quoteslove.ui.allquotes.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
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
                this@AllQuotesFragment,
                viewModel.quotes
            ){
                updatedFiltersCollapsingCoordinator(it)
            }
    }

    override fun onResume() {
        super.onResume()
        setupToolBar(R.string.action_tile_all_quotes)
    }

    private fun updatedFiltersCollapsingCoordinator(isEmpty: Boolean) {
        val params = binding.filtersCollapsingLayout.layoutParams as AppBarLayout.LayoutParams
        val lm = binding.rvQuotes.layoutManager as LinearLayoutManager
        binding.rvQuotes.post {
            // Enable collapsing if recycler scrollable otherwise disable if all items visible on screen and un-scrollable
            if (isEmpty || (lm.findFirstCompletelyVisibleItemPosition() == 0
                        && lm.findLastCompletelyVisibleItemPosition() == lm.itemCount - 1)) {
                params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
            } else {
                params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            }
            binding.filtersCollapsingLayout.layoutParams = params
        }
    }
}