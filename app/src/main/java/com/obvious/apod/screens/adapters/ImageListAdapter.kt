package com.obvious.apod.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.obvious.apod.databinding.HolderImageBinding
import com.obvious.apod.listeners.ImageListAdapterListener
import com.obvious.apod.models.ImageDataModel

class ImageListAdapter(
    private val itemList: List<ImageDataModel>,
    private val listener: ImageListAdapterListener,
) : RecyclerView.Adapter<ImageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val binding = HolderImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.setAnimation(position)
        holder.bind(itemList[position])
        holder.binding.ivImg.setOnClickListener {
            listener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}