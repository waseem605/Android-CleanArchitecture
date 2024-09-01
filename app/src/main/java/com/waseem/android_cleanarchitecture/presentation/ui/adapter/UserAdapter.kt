package com.waseem.android_cleanarchitecture.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.clean_architecture_kotlin.data.model.User
import com.waseem.android_cleanarchitecture.presentation.ui.viewholder.AllUserViewHolder
import com.waseem.android_cleanarchitecture.databinding.UserItemBinding

class UserAdapter(
    private val data: ArrayList<User>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AllUserViewHolder(getView(parent))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder:  id = ${data[position].id} userName = ${data[position].username} ${data[position].password}")
        (holder as AllUserViewHolder).onBind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    private fun getView(parent: ViewGroup?): UserItemBinding {
        return UserItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    }
}