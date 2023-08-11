package com.compose.business.home.mapper

import com.compose.business.home.model.CryptoBusinessModel
import com.compose.business.home.repository.HomeCryptoModelInterface

class CryptoBusinessModelMapper {

    fun toBusinessModel(
        cryptoDataModel: HomeCryptoModelInterface,
        cryptoLogoUrl: String
    ): CryptoBusinessModel {
        return CryptoBusinessModel(
            cryptoDataModel.id.hashCode(),
            cryptoDataModel.symbol,
            getCryptoLogoUrl(cryptoLogoUrl, cryptoDataModel.symbol),
            cryptoDataModel.name,
            cryptoDataModel.image,
            cryptoDataModel.currentPrice,
            cryptoDataModel.marketCap,
            cryptoDataModel.marketCapRank,
            cryptoDataModel.totalVolume,
            cryptoDataModel.highPrice24,
            cryptoDataModel.lowPrice24,
            cryptoDataModel.priceChange24,
            cryptoDataModel.priceChangePercentage24,
            cryptoDataModel.totalSupply,
            cryptoDataModel.maxSupply,
            cryptoDataModel.ath,
            cryptoDataModel.athChangePercentage,
            cryptoDataModel.athDate
        )
    }

    private fun getCryptoLogoUrl(cryptoLogoUrl: String, symbol: String): String {
        return cryptoLogoUrl.replace(CRYPTO_URL_ID_KEY, symbol.lowercase())
    }

    companion object {
        const val CRYPTO_URL_ID_KEY = "[cryptoID]"
    }
}