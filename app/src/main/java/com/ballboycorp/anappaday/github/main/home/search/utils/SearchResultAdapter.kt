package com.ballboycorp.anappaday.github.main.home.search.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ballboycorp.anappaday.github.databinding.ItemSearchUserBinding
import com.ballboycorp.anappaday.github.main.home.search.SearchItemViewModel
import com.ballboycorp.anappaday.github.model.user.User

/**
 * Created by musooff on 2019-07-08.
 */

class SearchResultAdapter: PagedListAdapter<User, SearchResultAdapter.SearchResultViewHolder>(
    ItemDiffCallBack
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemSearchUserBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.viewModel = SearchItemViewModel(it)
        }
    }

    inner class SearchResultViewHolder(val binding: ItemSearchUserBinding): RecyclerView.ViewHolder(binding.root)
}

object ItemDiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}