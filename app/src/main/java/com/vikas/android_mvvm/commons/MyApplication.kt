package com.vikas.android_mvvm.commons

import android.app.Application
import com.vikas.android_mvvm.commons.dagger.AppComponent
import com.vikas.android_mvvm.commons.dagger.DaggerAppComponent
import com.vikas.android_mvvm.models.services.RetrofitAdapter

class MyApplication : Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().retrofitAdapter(RetrofitAdapter()).build()
    }
}