package com.compose.business.home.mapper

import com.compose.business.testmodels.HomeCryptoModelTestProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test


class CryptoViewModelMapperTest {

    private val cryptoViewModelMapper = CryptoViewModelMapper()

    @Test
    fun GIVEN_CryptoBusinessModel_WHEN_toViewModel_THEN_return_correct_result() {
        val inputCryptoBusinessModel =
            HomeCryptoModelTestProvider().getHomeCryptoTestModels(1, false).first()

        val cryptoViewModelResult =
            cryptoViewModelMapper.toViewModel(inputCryptoBusinessModel, "logoUrl")

        assertEquals(inputCryptoBusinessModel.id.hashCode(), cryptoViewModelResult.id)
        assertEquals(inputCryptoBusinessModel.symbol, cryptoViewModelResult.symbol)
        assertEquals("logoUrl", cryptoViewModelResult.logo)
        assertEquals(inputCryptoBusinessModel.title, cryptoViewModelResult.title)
        assertEquals(inputCryptoBusinessModel.image, cryptoViewModelResult.image)
        assertEquals(inputCryptoBusinessModel.currentPrice, cryptoViewModelResult.currentPrice)
        assertEquals(inputCryptoBusinessModel.marketCap, cryptoViewModelResult.marketCap)
        assertEquals(inputCryptoBusinessModel.marketCapRank, cryptoViewModelResult.marketCapRank)
        assertEquals(inputCryptoBusinessModel.totalVolume, cryptoViewModelResult.totalVolume)
        assertEquals(inputCryptoBusinessModel.highPrice24, cryptoViewModelResult.highPrice24)
        assertEquals(inputCryptoBusinessModel.lowPrice24, cryptoViewModelResult.lowPrice24)
        assertEquals(inputCryptoBusinessModel.priceChange24, cryptoViewModelResult.priceChange24)
        assertEquals(
            inputCryptoBusinessModel.priceChangePercentage24,
            cryptoViewModelResult.priceChangePercentage24
        )
        assertEquals(inputCryptoBusinessModel.totalSupply, cryptoViewModelResult.totalSupply)
        assertEquals(inputCryptoBusinessModel.maxSupply, cryptoViewModelResult.maxSupply)
        assertEquals(inputCryptoBusinessModel.ath, cryptoViewModelResult.ath)
        assertEquals(
            inputCryptoBusinessModel.athChangePercentage,
            cryptoViewModelResult.athChangePercentage
        )
        assertEquals(inputCryptoBusinessModel.athDate, cryptoViewModelResult.athDate)
    }
}