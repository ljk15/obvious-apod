package com.obvious.apod.screens.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.models.ResponseListener
import com.obvious.apod.repositories.SourceDataRepository

class LandingViewModel(application: Application) : AndroidViewModel(application) {

    val sourceLiveData = MutableLiveData<List<ImageDataModel>>()

    fun fetchData() {
        SourceDataRepository.readSourceData(getApplication(), object :
        ResponseListener<List<ImageDataModel>>{
            override fun toggleLoading(show: Boolean) {
                //todo loader
            }

            override fun onResponse(sourceData: List<ImageDataModel>) {
                sourceLiveData.postValue(sourceData.sortedByDescending { it.date })
            }

            override fun onError(error: String) {
                //todo error
            }
        })
    }

}