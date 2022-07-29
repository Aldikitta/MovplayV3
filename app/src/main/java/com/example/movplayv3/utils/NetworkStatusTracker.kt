package com.example.movplayv3.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map

class NetworkStatusTracker(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val connectionStatus = callbackFlow {
        var currentStatus: NetworkStatus? = null

        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                currentStatus?.let {
                    trySend(NetworkStatus.Disconnected)
                }.also {
                    currentStatus = NetworkStatus.Disconnected
                }
            }

            override fun onAvailable(network: Network) {
                currentStatus?.let {
                    trySend(NetworkStatus.Connected)
                }.also {
                    currentStatus = (NetworkStatus.Connected)
                }
            }

            override fun onLost(network: Network) {
                currentStatus?.let {
                    trySend(NetworkStatus.Disconnected)
                }.also {
                    currentStatus = (NetworkStatus.Disconnected)
                }
            }
        }


        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }

}

inline fun <Result> Flow<NetworkStatus>.map(
    crossinline onUnavailable: suspend () -> Result,
    crossinline onAvailable: suspend () -> Result,
): Flow<Result> = map { status ->
    when (status) {
        NetworkStatus.Disconnected -> onUnavailable()
        NetworkStatus.Connected -> onAvailable()
    }
}

sealed class NetworkStatus {
    object Disconnected : NetworkStatus()
    object Connected : NetworkStatus()
}