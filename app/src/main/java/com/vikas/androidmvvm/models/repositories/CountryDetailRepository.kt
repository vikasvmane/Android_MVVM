package com.vikas.androidmvvm.models.repositories

import com.vikas.androidmvvm.models.dataclasses.FactsResponse
import com.vikas.androidmvvm.models.services.RemoteServiceInterface
import retrofit2.Call
import retrofit2.Callback

class CountryDetailRepository(private val serviceInterface : RemoteServiceInterface) {

    fun fetchCountryDetail(callback :Callback<FactsResponse>): Call<FactsResponse> {
        val disposable = serviceInterface.getCountryDetails()
        disposable.enqueue(callback)
        return disposable
    }
}