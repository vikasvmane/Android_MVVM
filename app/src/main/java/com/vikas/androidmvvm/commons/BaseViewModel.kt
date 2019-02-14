package com.vikas.androidmvvm.commons

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application){
    var errorMsg = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

}