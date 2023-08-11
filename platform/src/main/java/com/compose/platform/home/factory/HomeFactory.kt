package com.compose.platform.home.factory

import com.compose.business.home.model.CryptoBusinessModel
import com.compose.platform.home.model.CryptoHomeViewElement

class HomeFactory {
    fun generateViewElements(cryptoBusinessModels: List<CryptoBusinessModel>): List<CryptoHomeViewElement> {
        return cryptoBusinessModels.map { cryptoBusinessModel ->
            CryptoHomeViewElement(
                cryptoBusinessModel.id,
                cryptoBusinessModel.symbol,
                cryptoBusinessModel.title,
                cryptoBusinessModel.logo,
                cryptoBusinessModel.image,
                cryptoBusinessModel.currentPrice,
                cryptoBusinessModel.marketCap,
                cryptoBusinessModel.marketCapRank,
                cryptoBusinessModel.totalVolume,
                cryptoBusinessModel.highPrice24,
                cryptoBusinessModel.lowPrice24,
                cryptoBusinessModel.priceChange24,
                cryptoBusinessModel.priceChangePercentage24,
                cryptoBusinessModel.totalSupply,
                cryptoBusinessModel.maxSupply,
                cryptoBusinessModel.ath,
                cryptoBusinessModel.athChangePercentage,
                cryptoBusinessModel.athDate
            )
        }
    }
}