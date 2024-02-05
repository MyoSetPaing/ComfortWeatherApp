package com.myosetpaing.data.internet

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.myosetpaing.domain.internet.InternetConnectionChecker
import javax.inject.Inject

class InternetConnectionCheckerImpl @Inject constructor(private val application: Application) :
    InternetConnectionChecker {

    override fun isNetworkAvailable():Boolean {

        val connectivityManager =
            application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))

    }

}