package com.vikas.androidmvvm.commons

import android.content.Context
import android.net.ConnectivityManager

class Utils {
    companion object {
        fun isInternetConnected(context: Context) : Boolean{
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            if(info!=null && info.isConnected)
                return true
            return false
        }
    }
}