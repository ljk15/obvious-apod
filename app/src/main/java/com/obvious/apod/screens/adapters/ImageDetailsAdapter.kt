package com.obvious.apod.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.obvious.apod.databinding.HolderDetailsBinding
import com.obvious.apod.models.ImageDataModel

class ImageDetailsAdapter(
    private val itemList: List<ImageDataModel>,
) : RecyclerView.Adapter<ImageDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailViewHolder {
        val binding = HolderDetailsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageDetailViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}