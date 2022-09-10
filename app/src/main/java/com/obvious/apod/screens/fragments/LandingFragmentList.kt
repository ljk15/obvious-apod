package com.obvious.apod.screens.fragments

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.fragment.app.activityViewModels
import com.obvious.apod.base.BaseViewBindingFragment
import com.obvious.apod.databinding.FragmentLandingListBinding
import com.obvious.apod.listeners.ImageListAdapterListener
import com.obvious.apod.screens.adapters.ImageListAdapter
import com.obvious.apod.screens.viewmodels.LandingViewModel

class LandingFragmentList :
    BaseViewBindingFragment<FragmentLandingListBinding>(FragmentLandingListBinding::inflate) {

    private val landingViewModel: LandingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        landingViewModel.sourceLiveData.observe(viewLifecycleOwner) {
            val mAdapter = ImageListAdapter(it, object : ImageListAdapterListener{
                override fun onClick(view: View, position: Int) {
                    view.isHapticFeedbackEnabled = true
                    view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                }
            })
            binding.rvItems.adapter = mAdapter
        }
        landingViewModel.fetchData()
    }
}