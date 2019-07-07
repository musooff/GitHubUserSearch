package com.ballboycorp.anappaday.github.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballboycorp.anappaday.github.base.BaseFragment
import com.ballboycorp.anappaday.github.databinding.FragmentHomeBinding
import com.ballboycorp.anappaday.github.main.home.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by musooff on 2019-07-05.
 */

class HomeFragment: BaseFragment() {

    private val pagerAdapter by lazy { HomePagerAdapter(childFragmentManager) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize(){
        initializeViews()
    }

    private fun initializeViews() {

        viewPager.adapter = pagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
}