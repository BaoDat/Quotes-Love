package com.mrtdev.quoteslove.ui.love.view

import android.os.Bundle
import com.mrtdev.quoteslove.R
import com.mrtdev.quoteslove.base.BaseFragment
import com.mrtdev.quoteslove.databinding.FragmentFavoritesBinding
import com.mrtdev.quoteslove.ui.love.viewmodel.FavoritesViewModel

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    override val layoutId = R.layout.fragment_favorites
    override val viewModelClass = FavoritesViewModel::class

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}