package com.obvious.apod.screens.activities

import android.os.Bundle
import com.obvious.apod.base.BaseViewBindingActivity
import com.obvious.apod.databinding.ActivityLandingBinding
import com.obvious.apod.utils.ConnectionStateMonitor

class LandingActivity :
    BaseViewBindingActivity<ActivityLandingBinding>(ActivityLandingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConnectionStateMonitor(this).observe(this) {
                if (it) {
                    //todo connected
                } else {
                    //todo disconnected
                }
        }
    }

}