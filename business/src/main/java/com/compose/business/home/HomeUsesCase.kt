package com.compose.business.home

import com.compose.business.common.model.AppError
import com.compose.business.common.model.BusinessResponse
import com.compose.business.common.model.Failure
import com.compose.business.common.model.Success
import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.mapper.CryptoViewModelMapper
import com.compose.business.home.model.CryptoViewModel
import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.business.home.repository.RepositoryFailure
import com.compose.business.home.repository.RepositorySuccess
import javax.inject.Inject

class HomeUsesCase @Inject constructor(
    private val homeRepository: HomeRepositoryInterface,
    private val remoteConfigGateway: RemoteConfigGatewayInterface,
    private val cryptoViewModelMapper: CryptoViewModelMapper
) : HomeUsesCaseInterface {

    override suspend fun fetchHomeData(): BusinessResponse<List<CryptoViewModel>> {
        return when (val dataResponse = homeRepository.fetchHomeData()) {
            is RepositorySuccess -> {
                Success(dataResponse.response
                    .asSequence()
                    .sortedBy { it.marketCapRank }
                    .take(remoteConfigGateway.homeVisibleCryptoCount)
                    .map { cryptoDataModel ->
                        cryptoViewModelMapper.toViewModel(
                            cryptoDataModel,
                            remoteConfigGateway.cryptoLogoUrl
                        )
                    }
                    .toList()
                )
            }
            is RepositoryFailure -> Failure(dataResponse.appError)
        }
    }
}