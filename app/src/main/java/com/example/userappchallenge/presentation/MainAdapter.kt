package com.example.userappchallenge.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.userappchallenge.databinding.UserHolderBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.UserViewHolder>() {
    private val userList: MutableList<UserViewData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            UserHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun updateUserData(data: List<UserViewData>) {
        userList.clear()
        userList.addAll(data)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: UserHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserViewData) {
            binding.apply {
                userNameTextView.text = item.fullName
                userNationalityTextView.text = item.nationality
                userProfileImageView.load(item.profilePic)
            }
        }
    }
}