package com.mrtdev.quoteslove.ui.allquotes.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseActivity
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.common.dialog.BottomSheetFragment
import com.mrtdev.quoteslove.database.models.Quote
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
                viewModel.quotes,
                this::updatedFiltersCollapsingCoordinator,
                this::showBottomSheet
            )
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

    private fun showBottomSheet(quote: Quote) {
//        val view: View = layoutInflater.inflate(R.layout.bottom_sheet, null)
//
//        val dialog = context?.let { BottomSheetDialog(it) }
//        dialog!!.setContentView(view)
//        dialog.show()
        (context as? BaseActivity)?.apply {
            val fragment = BottomSheetFragment.newInstance(quote)
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack(BottomSheetFragment::class.java.simpleName)
//                .commitAllowingStateLoss()
            supportFragmentManager.let {
                BottomSheetFragment.newInstance(quote).apply {
                    show(it, tag)
                }
            }
        }
    }
}