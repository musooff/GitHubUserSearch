package com.ballboycorp.anappaday.github.main.home.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ballboycorp.anappaday.github.databinding.ItemFavoriteUserBinding
import com.ballboycorp.anappaday.github.main.home.favorites.FavoriteItemViewModel
import com.ballboycorp.anappaday.github.model.user.User

/**
 * Created by musooff on 2019-07-05.
 */

class FavoritesRecyclerAdapter : RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesViewHolder>() {

    private var mUsers: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = ItemFavoriteUserBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return FavoritesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    fun submitList(users: List<User>) {
        mUsers = users
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.binding.viewModel = FavoriteItemViewModel(mUsers[position])
    }

    inner class FavoritesViewHolder(val binding: ItemFavoriteUserBinding): RecyclerView.ViewHolder(binding.root)
}