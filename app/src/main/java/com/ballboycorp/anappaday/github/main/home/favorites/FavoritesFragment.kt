package com.ballboycorp.anappaday.github.main.home.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ballboycorp.anappaday.github.base.BaseFragment
import com.ballboycorp.anappaday.github.databinding.FragmentFavoritesBinding
import com.ballboycorp.anappaday.github.main.home.favorites.adapter.FavoritesRecyclerAdapter
import com.ballboycorp.anappaday.github.utils.extensions.getViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * Created by musooff on 2019-07-05.
 */

class FavoritesFragment: BaseFragment() {

    private val favoritesAdapter = FavoritesRecyclerAdapter()

    private val viewModel by lazy { getViewModel<FavoritesViewModel>() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initializeViews()

        initializeViewModel()
    }

    private fun initializeViews() {

        rvFavorites.adapter = favoritesAdapter
    }

    private fun initializeViewModel() {

        viewModel.users.observe(this, Observer {
            favoritesAdapter.submitList(it)
        })
    }
}