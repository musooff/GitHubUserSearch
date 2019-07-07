package com.ballboycorp.anappaday.github.main.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ballboycorp.anappaday.github.main.home.favorites.FavoritesFragment
import com.ballboycorp.anappaday.github.main.home.search.SearchFragment

/**
 * Created by musooff on 2019-07-05.
 */

class HomePagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SearchFragment()
            1 -> FavoritesFragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "검색"
            1 -> "좋아요"
            else -> ""
        }
    }
}