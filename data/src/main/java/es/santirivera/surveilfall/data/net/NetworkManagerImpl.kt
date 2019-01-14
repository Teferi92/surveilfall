package es.santirivera.surveilfall.data.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import es.santirivera.surveilfall.data.exceptions.NetworkUnavailableException

class NetworkManagerImpl(private val context: Context) : NetworkManager {

    /**
     * Checks if there is any active network
     */
    override fun isInternetAvailable(context: Context): Boolean {
        val activeNetwork = getActiveNetwork(context)
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    /**
     * Checks if the active connection is [ConnectivityManager.TYPE_WIFI]
     */
    private fun isWifiConnected(context: Context): Boolean {
        val activeNetwork = getActiveNetwork(context)
        return activeNetwork != null && activeNetwork.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * Get the active [NetworkInfo]
     */
    private fun getActiveNetwork(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    /**
     * Checks if Internet is available or throws a [NetworkUnavailableException]
     *
     * @throws NetworkUnavailableException when internet is not available
     */
    @Throws(NetworkUnavailableException::class)
    override fun checkConnectivity() {
        if (!isInternetAvailable(context)) {
            throw NetworkUnavailableException()
        }
    }

    /**
     * Returns if the active network is [ConnectivityManager.TYPE_WIFI]. If there is no internet will throw a [NetworkUnavailableException]
     *
     * @throws NetworkUnavailableException when internet is not available
     */
    @Throws(NetworkUnavailableException::class)
    override fun checkWifi(): Boolean {
        checkConnectivity()
        return isWifiConnected(context)
    }
}
