package com.obvious.apod.screens.adapters

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.apod.R
import com.obvious.apod.databinding.HolderImageBinding
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.utils.Injector
import com.obvious.apod.utils.getFileName

class ImageListViewHolder(val binding: HolderImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageDataModel) {

        Glide.with(binding.ivImg.context)
            .load(item.hdUrl)
            .thumbnail(0.25f)
            .placeholder(Injector.provideShimmerPlaceHolder())
            .error(R.drawable.ic_twotone_image_failed_to_load_24)
            .centerCrop()
            .dontAnimate()
            .into(binding.ivImg)

        binding.ivImg.contentDescription =
            item.hdUrl?.getFileName() ?: item.mediaType
        binding.ivImg.transitionName =
            item.hdUrl?.getFileName() ?: item.mediaType

    }

    fun setAnimation(position: Int) {
        val animation: Animation = if (position % 2 == 0) {
            AnimationUtils.loadAnimation(binding.root.context, R.anim.right_from_left)
        } else {
            AnimationUtils.loadAnimation(binding.root.context, R.anim.left_from_right)
        }
        binding.root.startAnimation(animation)
    }

}