package com.vikas.androidmvvm.commons.dagger

import com.vikas.androidmvvm.models.services.RetrofitAdapter
import com.vikas.androidmvvm.viewmodels.CountryDetailViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitAdapter::class])
interface AppComponent {

    fun inject(countryDetailViewModel : CountryDetailViewModel)
}