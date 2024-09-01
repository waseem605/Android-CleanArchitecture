package com.waseem.android_cleanarchitecture.presentation.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.app.clean_architecture_kotlin.data.model.User
import com.waseem.android_cleanarchitecture.R
import com.waseem.android_cleanarchitecture.data.model.cartsModel.Carts
import com.waseem.android_cleanarchitecture.presentation.ui.viewholder.AllUserViewHolder
import com.waseem.android_cleanarchitecture.databinding.UserItemBinding

class CartAdapter(
    private val data: ArrayList<Carts>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AllUserViewHolder(getView(parent))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder:  id = ${data[position].id} userName = ${data[position]} ")
        (holder as AllUserViewHolder).onBind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    private fun getView(parent: ViewGroup?): UserItemBinding {
        return UserItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    }


    class AllUserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(user: Carts, position: Int) {
            binding.email.text = "discountedTotal ${user.discountedTotal}"
            binding.name.text ="totalProducts ${user.totalProducts}"

//            binding.userImage.load(user.image) {
//                crossfade(true)
//                placeholder(R.drawable.ic_user)
//                transformations(CircleCropTransformation())
//            }
        }
    }
}