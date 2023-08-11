package com.compose.data.home.repositroy

import com.compose.business.home.repository.HomeCryptoModelInterface
import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.business.home.repository.RepositoryResponseInterface
import com.compose.data.common.RepositorySuccess
import com.compose.data.home.datasource.HomeLocalDataSource
import com.compose.data.home.datasource.HomeRemoteDataSource
import com.compose.data.remoteconfig.RemoteConfigRepository

class HomeRepository(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val remoteConfigRepository: RemoteConfigRepository
) : HomeRepositoryInterface {

    override suspend fun fetchHomeData(): RepositoryResponseInterface<List<HomeCryptoModelInterface>> {
        homeLocalDataSource.data?.let { safeLocalData ->
            return RepositorySuccess(safeLocalData)
        }

        val remoteResponse = homeRemoteDataSource.fetch(remoteConfigRepository.cryptoDataUrl)
        homeLocalDataSource.cacheResponse(remoteResponse)
        return remoteResponse
    }
}