package com.ballboycorp.anappaday.github.main.home.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ballboycorp.anappaday.github.base.BaseFragment
import com.ballboycorp.anappaday.github.databinding.FragmentSearchBinding
import com.ballboycorp.anappaday.github.main.home.search.adapter.SearchResultAdapter
import com.ballboycorp.anappaday.github.utils.extensions.getViewModel
import com.ballboycorp.anappaday.github.utils.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by musooff on 2019-07-05.
 */

class SearchFragment: BaseFragment() {

    private val viewModel by lazy { getViewModel<SearchViewModel>() }
    private val resultAdapter = SearchResultAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
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

        rvSearchResults.adapter = resultAdapter

        searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onClickSearch()
                searchEditText.hideKeyboard()
            }
            return@OnEditorActionListener true
        })


    }

    private fun initializeViewModel() {

        viewModel.searchResults.observe(this, Observer {
            resultAdapter.submitList(it)
        })
    }
}