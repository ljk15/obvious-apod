package com.obvious.apod.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.obvious.apod.R
import com.obvious.apod.base.BaseViewBindingFragment
import com.obvious.apod.databinding.FragmentLandingDetailsBinding
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.screens.adapters.ImageDetailsAdapter
import com.obvious.apod.screens.transformers.ViewPagerParallaxTransformer
import com.obvious.apod.screens.transformers.ViewPagerTranslationTransformer
import com.obvious.apod.screens.viewmodels.LandingViewModel
import com.obvious.apod.utils.PaletteHelper
import com.obvious.apod.utils.colorTransition
import com.obvious.apod.utils.px

class LandingFragmentDetails :
    BaseViewBindingFragment<FragmentLandingDetailsBinding>(FragmentLandingDetailsBinding::inflate) {

    private val landingViewModel: LandingViewModel by activityViewModels()
    private val sourceDataList = ArrayList<ImageDataModel>()
    private lateinit var imgDetailsAdapter: ImageDetailsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupViewColors()
        landingViewModel.sourceLiveData.observe(viewLifecycleOwner) {
            sourceDataList.clear()
            sourceDataList.addAll(it)
            imgDetailsAdapter.notifyDataSetChanged()
            binding.vpDetails.setCurrentItem(landingViewModel.currentIndex, false)
        }
    }

    private fun setupView() {
        imgDetailsAdapter = ImageDetailsAdapter(sourceDataList)
        binding.vpDetails.offscreenPageLimit = 1
        val dp = 8
        binding.vpDetails.setPageTransformer(CompositePageTransformer().also {
            it.addTransformer(ViewPagerTranslationTransformer())
            it.addTransformer(ViewPagerParallaxTransformer(R.id.ivImg))
            it.addTransformer(MarginPageTransformer(dp.px))
        })
        binding.vpDetails.adapter = imgDetailsAdapter
    }

    private fun setupViewColors() {
        binding.vpDetails.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position != NO_POSITION) {
                    landingViewModel.currentIndex = position
                    if (!sourceDataList[position].hdUrl.isNullOrEmpty())
                        PaletteHelper.getColorFromImage(sourceDataList[position].hdUrl!!,
                            binding.vpDetails) {
                            binding.vpDetails.colorTransition(it)
                        }
                }
            }
        })
    }
}