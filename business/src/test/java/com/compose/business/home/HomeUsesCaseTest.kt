package com.compose.business.home

import com.compose.business.common.model.AppError
import com.compose.business.common.model.Failure
import com.compose.business.common.model.Success
import com.compose.business.gateway.RemoteConfigGatewayInterface
import com.compose.business.home.mapper.CryptoBusinessModelMapper
import com.compose.business.home.repository.HomeCryptoModelInterface
import com.compose.business.home.repository.HomeRepositoryInterface
import com.compose.business.home.repository.RepositoryFailureInterface
import com.compose.business.home.repository.RepositoryResponseInterface
import com.compose.business.testmodels.HomeCryptoModelTestProvider
import com.compose.business.testmodels.RepositoryFailureTestModel
import com.compose.business.testmodels.RepositorySuccessTestModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class HomeUsesCaseTest {

    private val homeRepository: HomeRepositoryInterface = mockk()
    private val remoteConfigGateway: RemoteConfigGatewayInterface = mockk()
    private val cryptoBusinessModelMapper: CryptoBusinessModelMapper = CryptoBusinessModelMapper()

    private val homeUsesCase =
        HomeUsesCase(homeRepository, remoteConfigGateway, cryptoBusinessModelMapper)

    private val homeCryptoModelTestProvider = HomeCryptoModelTestProvider()

    @Test
    fun GIVEN_success_repository_response_WHEN_fetchHomeDate_THEN_return_correct_result() =
        runTest {
            // given
            val repositoryResponse: RepositoryResponseInterface<List<HomeCryptoModelInterface>> =
                RepositorySuccessTestModel(
                    homeCryptoModelTestProvider.getHomeCryptoTestModels(20, false)
                )
            coEvery { homeRepository.fetchHomeData() } coAnswers { repositoryResponse }
            val visibleCryptoCount = 3
            every { remoteConfigGateway.homeVisibleCryptoCount } returns visibleCryptoCount
            every { remoteConfigGateway.cryptoLogoUrl } returns "www.exampleurl.fr"

            // when
            val result = homeUsesCase.fetchHomeData()

            // then
            val successResult = result as Success
            assertEquals(visibleCryptoCount, successResult.result.size)
            assertEquals("0".hashCode(), successResult.result[0].id)
            assertEquals("1".hashCode(), successResult.result[1].id)
            assertEquals("2".hashCode(), successResult.result[2].id)
        }

    @Test
    fun GIVEN_success_repository_response_and_reversed_data_WHEN_fetchHomeDate_THEN_return_correct_result() =
        runTest {
            // given
            val repositoryResponse: RepositoryResponseInterface<List<HomeCryptoModelInterface>> =
                RepositorySuccessTestModel(
                    homeCryptoModelTestProvider.getHomeCryptoTestModels(20, true)
                )
            coEvery { homeRepository.fetchHomeData() } coAnswers { repositoryResponse }
            val visibleCryptoCount = 3
            every { remoteConfigGateway.homeVisibleCryptoCount } returns visibleCryptoCount
            every { remoteConfigGateway.cryptoLogoUrl } returns "www.exampleurl.fr"

            // when
            val result = homeUsesCase.fetchHomeData()

            // then
            val successResult = result as Success
            assertEquals(visibleCryptoCount, successResult.result.size)
            assertEquals("20".hashCode(), successResult.result[0].id)
            assertEquals("19".hashCode(), successResult.result[1].id)
            assertEquals("18".hashCode(), successResult.result[2].id)
        }

    @Test
    fun GIVEN_failed_repository_response_WHEN_fetchHomeDate_THEN_return_correct_result() =
        runTest {
            // given
            val repositoryResponse: RepositoryFailureInterface<List<HomeCryptoModelInterface>> =
                RepositoryFailureTestModel()
            coEvery { homeRepository.fetchHomeData() } coAnswers { repositoryResponse }

            // when
            val result = homeUsesCase.fetchHomeData()

            // then
            val failureResult = result as Failure
            assertEquals(AppError.UNKNOWN, failureResult.error)
        }
}