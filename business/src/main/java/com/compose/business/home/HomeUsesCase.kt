package com.compose.business.home

import com.compose.business.common.model.AppError
import com.compose.business.common.model.BusinessResponse
import com.compose.business.common.model.Failure
import com.compose.business.common.model.Success
import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.mapper.CryptoBusinessModelMapper
import com.compose.business.home.model.CryptoBusinessModel
import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.business.home.repository.RepositoryFailureInterface
import com.compose.business.home.repository.RepositorySuccessInterface

class HomeUsesCase(
    private val homeRepository: HomeRepositoryInterface,
    private val remoteConfigGateway: RemoteConfigGatewayInterface,
    private val cryptoBusinessModelMapper: CryptoBusinessModelMapper
) : HomeUsesCaseInterface {

    override suspend fun fetchHomeData(): BusinessResponse<List<CryptoBusinessModel>> {
        return when (val dataResponse = homeRepository.fetchHomeData()) {
            is RepositorySuccessInterface -> {
                Success(dataResponse.response
                    .asSequence()
                    .sortedBy { it.marketCapRank }
                    .take(remoteConfigGateway.homeVisibleCryptoCount)
                    .map { cryptoDataModel ->
                        cryptoBusinessModelMapper.toBusinessModel(
                            cryptoDataModel,
                            remoteConfigGateway.cryptoLogoUrl
                        )
                    }
                    .toList()
                )
            }

            is RepositoryFailureInterface -> Failure(AppError.UNKNOWN)
        }
    }
}