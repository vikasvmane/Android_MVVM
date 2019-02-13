package com.vikas.android_mvvm.viewmodels

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.vikas.android_mvvm.commons.BaseViewModel
import com.vikas.android_mvvm.commons.MyApplication
import com.vikas.android_mvvm.commons.dagger.AppComponent
import com.vikas.android_mvvm.models.dataclasses.FactsResponse
import com.vikas.android_mvvm.models.repositories.CountryDetailRepository
import com.vikas.android_mvvm.models.services.RemoteServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryDetailViewModel(application: Application) : BaseViewModel(application){
    var countryDetails = MutableLiveData<FactsResponse>()
    @Inject
    lateinit var serviceInterface : RemoteServiceInterface
    var countryDetailRepository : CountryDetailRepository

    init {
        MyApplication.appComponent.inject(this)
        countryDetailRepository = CountryDetailRepository(serviceInterface)
    }

    fun getCountryDetails() {
        countryDetailRepository.fetchCountryDetail(object : Callback<FactsResponse>{
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<FactsResponse>, t: Throwable) {

            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<FactsResponse>, response: Response<FactsResponse>) {
               countryDetails.value = response.body()
            }
        })
    }
}