package com.vikas.androidmvvm.commons

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

/**
 * Base class for ViewModels for accumulating common attributes to be used
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application){
    // For emitting error messages to the observers
    var errorMsg = MutableLiveData<String>()
    // For handling progressBar visibility on UI
    var isLoading = MutableLiveData<Boolean>()

}