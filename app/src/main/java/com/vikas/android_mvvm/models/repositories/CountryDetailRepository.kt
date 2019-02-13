package com.vikas.android_mvvm.models.repositories

import com.vikas.android_mvvm.models.dataclasses.FactsResponse
import com.vikas.android_mvvm.models.services.RemoteServiceInterface
import retrofit2.Callback

class CountryDetailRepository(private val serviceInterface : RemoteServiceInterface) {

    fun fetchCountryDetail(callback :Callback<FactsResponse>){
        serviceInterface.getCountryDetails().enqueue(callback)
    }
}