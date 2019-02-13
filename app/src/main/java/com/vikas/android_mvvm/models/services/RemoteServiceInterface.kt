package com.vikas.android_mvvm.models.services

import com.vikas.android_mvvm.models.dataclasses.FactsResponse
import retrofit2.Call
import retrofit2.http.GET

interface RemoteServiceInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountryDetails() : Call<FactsResponse>
}