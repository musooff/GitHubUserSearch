package com.ballboycorp.anappaday.github.main.home.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ballboycorp.anappaday.github.databinding.ItemUserBinding
import com.ballboycorp.anappaday.github.main.home.search.SearchItemViewModel
import com.ballboycorp.anappaday.github.model.user.User
import com.ballboycorp.anappaday.github.model.user.UserItemViewModel

/**
 * Created by musooff on 2019-07-05.
 */

class SearchResultAdapter: RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    private var mUsers: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return SearchResultViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    fun submitList(users: List<User>) {
        mUsers = users
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.binding.viewModel = SearchItemViewModel(mUsers[position])
    }

    inner class SearchResultViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
}