package com.vikas.androidmvvm.commons

import android.app.Application
import com.vikas.androidmvvm.commons.dagger.AppComponent
import com.vikas.androidmvvm.commons.dagger.DaggerAppComponent
import com.vikas.androidmvvm.models.services.RetrofitAdapter

class MyApplication : Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().retrofitAdapter(RetrofitAdapter()).build()
    }
}