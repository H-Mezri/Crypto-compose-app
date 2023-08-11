package com.compose.data.home.datasource

import com.compose.business.home.repository.HomeCryptoModelInterface
import com.compose.business.home.repository.RepositoryResponseInterface
import com.compose.business.home.repository.RepositorySuccessInterface

/**
 * In this demo app the local data source is a list
 * in real app we can use a database like ROOM or the SharedPref
 */
class HomeLocalDataSource {
    var data: List<HomeCryptoModelInterface>? = null

    fun cacheResponse(remoteResponse: RepositoryResponseInterface<List<HomeCryptoModelInterface>>) {
        if (remoteResponse is RepositorySuccessInterface) {
            data = remoteResponse.response
        }
    }
}