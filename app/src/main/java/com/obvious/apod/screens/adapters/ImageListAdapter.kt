package com.obvious.apod.screens.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.apod.R
import com.obvious.apod.databinding.HolderImageBinding
import com.obvious.apod.listeners.ImageListAdapterListener
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.utils.getFileName

class ImageListAdapter(
    private val itemList: List<ImageDataModel>,
    private val listener: ImageListAdapterListener,
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HolderImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setAnimation(holder.itemView, position)
        holder.binding.ivImg.contentDescription =
            itemList[position].url?.getFileName() ?: itemList[position].mediaType
        Glide.with(holder.binding.ivImg.context)
            .load(itemList[position].url)
            .centerCrop()
            .into(holder.binding.ivImg)
        holder.binding.ivImg.setOnClickListener {
            listener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        val animation: Animation = if (position % 2 == 0) {
            AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.right_from_left)
        } else {
            AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.left_from_right)
        }
        viewToAnimate.startAnimation(animation)
    }

    inner class ViewHolder(val binding: HolderImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}