package com.vikas.androidmvvm.models.repositories

import com.vikas.androidmvvm.models.dataclasses.FactsResponse
import com.vikas.androidmvvm.models.services.RemoteServiceInterface
import retrofit2.Call
import retrofit2.Callback

/**
 * One point of contact for data required by ViewModels. Part of Model layer
 */
class CountryDetailRepository(private val serviceInterface : RemoteServiceInterface) {

    /**
     * Fetches data from the endpoint using Retrofit
     * @param callback<T> to redirect the response from API call
     * @return Call<T> which can be used to cancel the API call
     */
    fun fetchCountryDetail(callback :Callback<FactsResponse>): Call<FactsResponse> {
        val call = serviceInterface.getCountryDetails()
        call.enqueue(callback)
        return call
    }
}