package com.obvious.apod.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class ConnectionStateMonitor(mContext: Context) : LiveData<Boolean>() {

    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private var connectivityManager: ConnectivityManager? = null

    init {
        connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkCallback = NetworkCallback(this)
    }

    override fun onActive() {
        super.onActive()
        connectivityManager!!.registerDefaultNetworkCallback(networkCallback!!)
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        connectivityManager!!.registerNetworkCallback(networkRequest, networkCallback!!)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager!!.unregisterNetworkCallback(networkCallback!!)
    }

    internal class NetworkCallback(private val mConnectionStateMonitor: ConnectionStateMonitor) :
        ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            mConnectionStateMonitor.postValue(true)
        }

        override fun onLost(network: Network) {
            mConnectionStateMonitor.postValue(false)
        }
    }

}