package com.obvious.apod.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.apod.R
import com.obvious.apod.databinding.HolderDetailsBinding
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.utils.Injector
import com.obvious.apod.utils.convertDate
import com.obvious.apod.utils.getFileName

class ImageDetailsAdapter(
    private val itemList: List<ImageDataModel>,
) : RecyclerView.Adapter<ImageDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HolderDetailsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        Glide.with(holder.binding.ivImg.context)
            .load(item.hdUrl)
            .placeholder(Injector.provideShimmerPlaceHolder())
            .into(holder.binding.ivImg)
        holder.binding.ivImg.contentDescription =
            item.url?.getFileName() ?: item.mediaType
        holder.binding.tvDate.text = item.date?.convertDate() ?: ""
        holder.binding.tvTitle.text = item.title
        holder.binding.tvCopyright.text = item.copyright
        if (item.copyright.isNullOrEmpty()) {
            holder.binding.div.visibility = View.GONE
        } else {
            holder.binding.div.visibility = View.VISIBLE
        }
        holder.binding.tvContent.text = item.explanation
        if (!item.mediaType.isNullOrEmpty())
            holder.binding.tvType.text = String.format("%s%s",
                holder.itemView.context.getString(R.string.media_type),
                item.mediaType)
        if (!item.serviceVersion.isNullOrEmpty())
            holder.binding.tvVersion.text = String.format("%s%s",
                holder.itemView.context.getString(R.string.service_version),
                item.serviceVersion)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(val binding: HolderDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)
}