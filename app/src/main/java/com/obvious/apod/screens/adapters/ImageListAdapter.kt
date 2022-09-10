package com.obvious.apod.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.apod.databinding.HolderImageBinding
import com.obvious.apod.listeners.ImageListAdapterListener
import com.obvious.apod.models.ImageDataModel

class ImageListAdapter(
    private val itemList: List<ImageDataModel>,
    private val listener: ImageListAdapterListener
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HolderImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    inner class ViewHolder(val binding: HolderImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}