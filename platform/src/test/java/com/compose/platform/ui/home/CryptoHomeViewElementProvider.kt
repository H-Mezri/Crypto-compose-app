package com.compose.platform.ui.home

import com.compose.platform.home.model.CryptoHomeViewElement

class CryptoHomeViewElementProvider {

    fun getHomeCryptoViewElements(size: Int): MutableList<CryptoHomeViewElement> {
        val homeViewElement = mutableListOf<CryptoHomeViewElement>()
        for (i in 0 until size) {
            homeViewElement.add(
                CryptoHomeViewElement(
                    id = i,
                    symbol = "symbol$i",
                    title = "title$i",
                    logoUrl = "www.logourl.fr",
                    image = "",
                    currentPrice = 1.0 * i,
                    marketCap = 10.0 * i,
                    marketCapRank = i,
                    totalVolume = 100.0 * i,
                    highPrice24 = 3.0 * i,
                    lowPrice24 = 0.5 * i,
                    priceChange24 = 0.5,
                    priceChangePercentage24 = 1.0,
                    totalSupply = 100.0 * i,
                    maxSupply = 1000.0 * i,
                    ath = 4.0 * i,
                    athChangePercentage = 0.5,
                    athDate = "2013-07-06T00:00:00.000Z"
                )
            )
        }
        return homeViewElement
    }
}