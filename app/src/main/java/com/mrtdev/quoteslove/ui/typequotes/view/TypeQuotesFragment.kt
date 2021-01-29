package com.mrtdev.quoteslove.ui.typequotes.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentTypeQuotesBinding
import com.mrtdev.quoteslove.ui.typequotes.viewmodel.TypeQuotesViewModel

class TypeQuotesFragment : BaseFragment<FragmentTypeQuotesBinding, TypeQuotesViewModel>() {

    companion object {
        fun newInstance(): TypeQuotesFragment {
            return TypeQuotesFragment()
        }
    }

    override val layoutId = R.layout.fragment_type_quotes
    override val viewModelClass = TypeQuotesViewModel::class

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        setupToolBar(R.string.action_tile_type_quote)
    }
}