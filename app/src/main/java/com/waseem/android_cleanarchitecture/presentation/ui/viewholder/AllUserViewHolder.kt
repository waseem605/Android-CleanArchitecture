package com.waseem.android_cleanarchitecture.presentation.ui.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.app.clean_architecture_kotlin.data.model.User
import com.waseem.android_cleanarchitecture.R
import com.waseem.android_cleanarchitecture.databinding.UserItemBinding

class AllUserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(user: User, position: Int) {
        binding.email.text = user.email
        binding.name.text = "${user.firstName} ${user.lastName}"

        binding.userImage.load(user.image) {
            crossfade(true)
            placeholder(R.drawable.ic_user)
            transformations(CircleCropTransformation())
        }
    }
}