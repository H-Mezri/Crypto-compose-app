package com.compose.business.testmodels

import com.compose.business.home.repository.HomeCryptoModelInterface

class HomeCryptoModelTestProvider {
    fun getHomeCryptoTestModels(size: Int, reversed: Boolean): MutableList<HomeCryptoModelInterface> {
        val homeCryptoModels = mutableListOf<HomeCryptoModelInterface>()
        for (i in 0..size) {
            homeCryptoModels.add(
                HomeCryptoTestModel(
                    id = i.toString(),
                    symbol = "symbol$i",
                    name = "name$i",
                    image = "",
                    currentPrice = 1.0 * i,
                    marketCap = 10.0 * i,
                    marketCapRank = if (reversed) size - i else i,
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
        return homeCryptoModels
    }
}