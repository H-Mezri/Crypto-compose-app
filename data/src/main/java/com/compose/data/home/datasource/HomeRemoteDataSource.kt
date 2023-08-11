package com.compose.data.home.datasource

import com.compose.business.home.repository.HomeCryptoModelInterface
import com.compose.business.home.repository.RepositoryResponseInterface
import com.compose.data.common.RepositoryFailure
import com.compose.data.common.RepositorySuccess

class HomeRemoteDataSource(private val homeDataApi: HomeDataApi) {

    suspend fun fetch(url: String): RepositoryResponseInterface<List<HomeCryptoModelInterface>> {
        return try {
            val homeResponse = homeDataApi.fetch(url)
            return when {
                homeResponse.isSuccessful -> {
                    homeResponse.body()?.let { safeResponseBody ->
                        RepositorySuccess(safeResponseBody)
                    } ?: run { RepositoryFailure() }
                }
                else -> {
                    RepositoryFailure()
                }
            }
        } catch (e: Exception) {
            RepositoryFailure()
        }
    }
}