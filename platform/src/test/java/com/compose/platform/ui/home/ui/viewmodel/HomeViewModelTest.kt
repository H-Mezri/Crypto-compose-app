package com.compose.platform.ui.home.ui.viewmodel

import com.compose.business.common.model.AppError
import com.compose.business.common.model.Failure
import com.compose.business.common.model.Success
import com.compose.business.home.HomeUsesCaseInterface
import com.compose.platform.ui.home.CryptoHomeViewElementProvider
import com.compose.platform.home.factory.HomeFactory
import com.compose.platform.home.model.OnRefresh
import com.compose.platform.home.model.OnStart
import com.compose.platform.home.ui.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class HomeViewModelTest {
    private val homeUsesCase: HomeUsesCaseInterface = mockk(relaxed = true)
    private val homeFactory: HomeFactory = mockk()

    private val homeViewModel: HomeViewModel = HomeViewModel(homeUsesCase, homeFactory)

    private val homeViewElementProvider = CryptoHomeViewElementProvider()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun GIVEN_OnStart_WHEN_sendAction_THEN_state_set_correctly() = runTest {
        // given
        val viewElementsSize = 5
        val action = OnStart
        coEvery { homeUsesCase.fetchHomeData() } coAnswers { Success(emptyList()) }
        every { homeFactory.generateViewElements(any()) } returns homeViewElementProvider.getHomeCryptoViewElements(
            viewElementsSize
        )

        // when
        homeViewModel.sendAction(action)

        // then
        advanceUntilIdle()
        coVerify { homeUsesCase.fetchHomeData() }
        val newState = homeViewModel.uiState.value
        assertEquals(viewElementsSize, newState.cryptoHomeViewElements.size)
        assertNull(newState.error)
        assertFalse(newState.isRefreshing)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun GIVEN_OnStart_and_failure_WHEN_sendAction_THEN_state_set_correctly() = runTest {
        // given
        val networkError = AppError.NETWORK_ERROR
        val action = OnStart
        coEvery { homeUsesCase.fetchHomeData() } coAnswers { Failure(networkError) }

        // when
        homeViewModel.sendAction(action)

        // then
        advanceUntilIdle()
        coVerify { homeUsesCase.fetchHomeData() }
        val newState = homeViewModel.uiState.value
        assertEquals(0, newState.cryptoHomeViewElements.size)
        assertNotNull(newState.error)
        assertEquals(networkError, newState.error)
        assertFalse(newState.isRefreshing)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun GIVEN_OnRefresh_WHEN_sendAction_THEN_state_set_correctly() = runTest {
        // given
        val viewElementsSize = 5
        val action = OnRefresh
        coEvery { homeUsesCase.fetchHomeData() } coAnswers { Success(emptyList()) }
        every { homeFactory.generateViewElements(any()) } returns homeViewElementProvider.getHomeCryptoViewElements(
            viewElementsSize
        )

        // when
        homeViewModel.sendAction(action)

        // then
        advanceUntilIdle()
        coVerify { homeUsesCase.fetchHomeData() }
        val newState = homeViewModel.uiState.value
        assertEquals(viewElementsSize, newState.cryptoHomeViewElements.size)
        assertNull(newState.error)
        assertFalse(newState.isRefreshing)
    }
}