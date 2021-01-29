package com.mrtdev.quoteslove.ui.love.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mrtdev.quoteslove.ui.love.view.FavoritesFragment
import com.mrtdev.quoteslove.ui.love.view.ListFragment

class LovePagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val PAGE_COUNT = 2
        const val LIKE_POSITION = 0
        const val LIST_POSITION = 1
    }

    override fun getCount(): Int = PAGE_COUNT

    override fun getItem(position: Int): Fragment {
        return when (position) {
            LIKE_POSITION -> FavoritesFragment.newInstance()
            LIST_POSITION -> ListFragment.newInstance()
            else -> throw IllegalStateException("Invalid index: position = $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            LIKE_POSITION -> "YÊU THÍCH"
            LIST_POSITION -> "DANH SÁCH"
            else -> ""
        }
    }
}