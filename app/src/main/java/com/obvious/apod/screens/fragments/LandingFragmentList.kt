package com.obvious.apod.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.fragment.app.activityViewModels
import com.obvious.apod.base.BaseViewBindingFragment
import com.obvious.apod.databinding.FragmentLandingListBinding
import com.obvious.apod.listeners.ImageListAdapterListener
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.screens.adapters.ImageListAdapter
import com.obvious.apod.screens.viewmodels.LandingViewModel
import com.obvious.apod.utils.RecyclerViewItemSpacingDecoration

class LandingFragmentList :
    BaseViewBindingFragment<FragmentLandingListBinding>(FragmentLandingListBinding::inflate) {

    private val landingViewModel: LandingViewModel by activityViewModels()
    private val sourceDataList = ArrayList<ImageDataModel>()
    private lateinit var imgListAdapter: ImageListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        landingViewModel.sourceLiveData.observe(viewLifecycleOwner) {
            sourceDataList.clear()
            sourceDataList.addAll(it)
            imgListAdapter.notifyDataSetChanged()
        }
        landingViewModel.fetchData()

    }

    private fun setupView() {
        imgListAdapter = ImageListAdapter(sourceDataList, object : ImageListAdapterListener{
            override fun onClick(view: View, position: Int) {
                view.isHapticFeedbackEnabled = true
                view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
            }
        })
        binding.rvItems.addItemDecoration(RecyclerViewItemSpacingDecoration(1, 1, 1, 1))
        binding.rvItems.adapter = imgListAdapter

    }
}