package com.obvious.apod.screens.activities

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.obvious.apod.base.BaseViewBindingActivity
import com.obvious.apod.databinding.ActivityLandingBinding
import com.obvious.apod.screens.viewmodels.LandingViewModel

class LandingActivity :
    BaseViewBindingActivity<ActivityLandingBinding>(ActivityLandingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mViewModel = ViewModelProvider(this)[LandingViewModel(application)::class.java]
        mViewModel.sourceLiveData.observe(this){
            //handle result
        }
        mViewModel.fetchData()
    }

}