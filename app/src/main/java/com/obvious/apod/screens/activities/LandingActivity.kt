package com.obvious.apod.screens.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View.GONE
import android.view.View.VISIBLE
import com.obvious.apod.R
import com.obvious.apod.base.BaseViewBindingActivity
import com.obvious.apod.databinding.ActivityLandingBinding
import com.obvious.apod.utils.ConnectionStateMonitor

class LandingActivity :
    BaseViewBindingActivity<ActivityLandingBinding>(ActivityLandingBinding::inflate) {

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var visibilityRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNetworkMonitor()
    }

    private fun setupNetworkMonitor() {
        visibilityRunnable = Runnable {
            binding.tvNoNet.visibility = GONE
        }

        ConnectionStateMonitor(this).observe(this) {
            if (it) {
                binding.tvNoNet.text = getString(R.string.network_connected)
                binding.tvNoNet.setBackgroundColor(getColor(R.color.green))
                binding.tvNoNet.visibility = VISIBLE
                handler.postDelayed(visibilityRunnable, 3000)
            } else {
                handler.removeCallbacks(visibilityRunnable)
                binding.tvNoNet.text = getString(R.string.working_offline)
                binding.tvNoNet.setBackgroundColor(getColor(R.color.red))
                binding.tvNoNet.visibility = VISIBLE
            }
        }
    }

}