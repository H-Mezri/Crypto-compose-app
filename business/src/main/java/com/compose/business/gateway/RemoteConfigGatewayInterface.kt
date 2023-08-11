package com.compose.business.gateway

interface RemoteConfigGatewayInterface {
    val cryptoDataUrl: String
    val cryptoLogoUrl: String
    val homeVisibleCryptoCount: Int
}