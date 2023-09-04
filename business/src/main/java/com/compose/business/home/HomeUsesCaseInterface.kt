package com.compose.business.home

import com.compose.business.common.model.BusinessResponse
import com.compose.business.home.model.CryptoViewModel

interface HomeUsesCaseInterface {
    suspend fun fetchHomeData(): BusinessResponse<List<CryptoViewModel>>
}