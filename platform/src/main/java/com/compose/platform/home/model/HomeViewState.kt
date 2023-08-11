package com.compose.platform.home.model

import com.compose.business.common.model.AppError

data class HomeViewState(
    val cryptoHomeViewElements: List<CryptoHomeViewElement> = emptyList(),
    val error: AppError? = null,
    val isRefreshing: Boolean = true
)