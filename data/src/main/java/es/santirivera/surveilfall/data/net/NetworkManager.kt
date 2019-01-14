package es.santirivera.surveilfall.data.net

import android.content.Context
import es.santirivera.surveilfall.data.exceptions.NetworkUnavailableException


interface NetworkManager {

    fun checkConnectivity()

    @Throws(NetworkUnavailableException::class)
    fun checkWifi(): Boolean

    fun isInternetAvailable(context: Context): Boolean
}
