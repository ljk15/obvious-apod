package com.obvious.apod.screens.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.apod.R
import com.obvious.apod.databinding.HolderDetailsBinding
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.utils.Injector
import com.obvious.apod.utils.convertDate
import com.obvious.apod.utils.getFileName

class ImageDetailViewHolder(private val binding: HolderDetailsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageDataModel) {
        Glide.with(binding.ivImg.context)
            .load(item.hdUrl)
            .placeholder(Injector.provideShimmerPlaceHolder())
            .dontAnimate()
            .into(binding.ivImg)

        binding.ivImg.contentDescription = item.url?.getFileName() ?: item.mediaType
        binding.tvDate.text = item.date?.convertDate() ?: ""
        binding.tvTitle.text = item.title
        binding.tvCopyright.text = item.copyright
        binding.tvContent.text = item.explanation

        if (item.copyright.isNullOrEmpty())
            binding.div.visibility = View.GONE
        else
            binding.div.visibility = View.VISIBLE

        if (!item.mediaType.isNullOrEmpty())
            binding.tvType.text = String.format("%s%s",
                itemView.context.getString(R.string.media_type),
                item.mediaType)

        if (!item.serviceVersion.isNullOrEmpty())
            binding.tvVersion.text = String.format("%s%s",
                itemView.context.getString(R.string.service_version),
                item.serviceVersion)
    }
}