package com.obvious.apod.screens.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.obvious.apod.listeners.ResponseListener
import com.obvious.apod.models.ImageDataModel
import com.obvious.apod.repositories.SourceDataRepository

class LandingViewModel(application: Application) : AndroidViewModel(application) {

    val sourceLiveData = MutableLiveData<List<ImageDataModel>>()
    var currentIndex: Int = 0
    val errorLiveData = MutableLiveData<String>()

    fun fetchData() {
        SourceDataRepository.readSourceData(getApplication(), object :
            ResponseListener<List<ImageDataModel>> {
            override fun toggleLoading(show: Boolean) {
                //if loader to be shown in future
            }

            override fun onSuccess(sourceData: List<ImageDataModel>) {
                sourceLiveData.postValue(sourceData.sortedByDescending { it.date })
            }

            override fun onError(error: String) {
                errorLiveData.postValue(error)
            }
        })
    }
}