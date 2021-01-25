package com.mrtdev.quoteslove.ui.likequotes.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseActivity
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentLikeQuotesBinding
import com.mrtdev.quoteslove.ui.likequotes.viewmodel.LikeQuotesViewModel

class LikeQuotesFragment : BaseFragment<FragmentLikeQuotesBinding, LikeQuotesViewModel>() {

    companion object {
        fun newInstance(): LikeQuotesFragment {
            return LikeQuotesFragment()
        }
    }

    override val layoutId = R.layout.fragment_like_quotes
    override val viewModelClass = LikeQuotesViewModel::class

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).apply {
            (activity as? BaseActivity)?.supportActionBar?.show()
        }
        setActionBarTitle("Yêu Thích Và Danh Sách")
    }
}