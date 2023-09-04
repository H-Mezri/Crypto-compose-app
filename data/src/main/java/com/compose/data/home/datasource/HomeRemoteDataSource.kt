package com.compose.data.home.datasource

import com.compose.business.common.model.AppError
import com.compose.business.home.repository.RepositoryFailure
import com.compose.business.home.repository.RepositoryResponse
import com.compose.business.home.repository.RepositorySuccess
import com.compose.data.home.model.CryptoDataModel

class HomeRemoteDataSource(private val homeDataApi: HomeDataApi) {

    suspend fun fetch(url: String): RepositoryResponse<List<CryptoDataModel>> {
        return try {
            val homeResponse = homeDataApi.fetch(url)
            return when {
                homeResponse.isSuccessful -> {
                    homeResponse.body()?.let { safeResponseBody ->
                        RepositorySuccess(safeResponseBody)
                    } ?: run { RepositoryFailure(AppError.EMPTY_RESPONSE) }
                }
                else -> {
                    RepositoryFailure(AppError.NETWORK_ERROR)
                }
            }
        } catch (e: Exception) {
            RepositoryFailure(AppError.UNKNOWN)
        }
    }
}