package com.vikas.android_mvvm.commons.dagger

import com.vikas.android_mvvm.models.services.RetrofitAdapter
import com.vikas.android_mvvm.viewmodels.CountryDetailViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitAdapter::class])
interface AppComponent {

    fun inject(countryDetailViewModel : CountryDetailViewModel)
}