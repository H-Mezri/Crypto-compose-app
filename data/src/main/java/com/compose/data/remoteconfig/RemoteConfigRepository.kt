package com.compose.data.remoteconfig

import com.compose.business.gateway.RemoteConfigGatewayInterface

class RemoteConfigRepository : RemoteConfigGatewayInterface {
    override val cryptoDataUrl: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=15&page=1&sparkline=false"
    override val cryptoLogoUrl: String = "https://assets.coincap.io/assets/icons/[cryptoID]@2x.png"
    override val homeVisibleCryptoCount: Int = 10
}