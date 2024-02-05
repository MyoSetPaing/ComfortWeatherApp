package com.myosetpaing.domain.internet

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface InternetConnectionChecker {
     fun isNetworkAvailable():Boolean
}