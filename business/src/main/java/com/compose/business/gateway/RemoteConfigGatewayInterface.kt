package com.compose.business.gateway

interface RemoteConfigGatewayInterface {
    val baseUrl: String
    val cryptoDataUrl: String
    val cryptoLogoUrl: String
    val homeVisibleCryptoCount: Int
}