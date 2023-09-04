package com.compose.data.home.datasource

import com.compose.data.home.model.CryptoDataModel

/**
 * In this demo app the local data source is a list
 * in real app we can use a database like ROOM or the SharedPref
 */
class HomeLocalDataSource {
    var data: List<CryptoDataModel>? = null

    fun cacheResponse(remoteResponse: List<CryptoDataModel>) {
        data = remoteResponse
    }
}