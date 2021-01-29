package com.mrtdev.quoteslove.ui.love.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentLoveBinding
import com.mrtdev.quoteslove.ui.love.adapter.LovePagerAdapter
import com.mrtdev.quoteslove.ui.love.viewmodel.LoveViewModel


class LoveFragment : BaseFragment<FragmentLoveBinding, LoveViewModel>() {

    companion object {
        fun newInstance(): LoveFragment {
            return LoveFragment()
        }
    }

    override val layoutId = R.layout.fragment_love
    override val viewModelClass = LoveViewModel::class

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.vpLike.adapter = LovePagerAdapter(requireContext(), childFragmentManager)
        binding.vpLike.currentItem = LovePagerAdapter.LIKE_POSITION
    }
}