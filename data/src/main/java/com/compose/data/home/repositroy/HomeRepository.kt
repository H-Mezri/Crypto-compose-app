package com.compose.data.home.repositroy

import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.model.CryptoBusinessModel
import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.business.home.repository.RepositoryFailure
import com.compose.business.home.repository.RepositoryResponse
import com.compose.business.home.repository.RepositorySuccess
import com.compose.data.home.datasource.HomeLocalDataSource
import com.compose.data.home.datasource.HomeRemoteDataSource
import com.compose.data.home.mapper.toBusinessModel

class HomeRepository(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val remoteConfigRepository: RemoteConfigGatewayInterface
) : HomeRepositoryInterface {

    override suspend fun fetchHomeData(): RepositoryResponse<List<CryptoBusinessModel>> {
        homeLocalDataSource.data?.let { safeLocalData ->
            return RepositorySuccess(safeLocalData.map { it.toBusinessModel() })
        }

        return when (val remoteResponse =
            homeRemoteDataSource.fetch(remoteConfigRepository.cryptoDataUrl)) {
            is RepositoryFailure -> RepositoryFailure(remoteResponse.appError)
            is RepositorySuccess -> {
                homeLocalDataSource.cacheResponse(remoteResponse.response)
                RepositorySuccess(remoteResponse.response.map { it.toBusinessModel() })
            }
        }
    }
}