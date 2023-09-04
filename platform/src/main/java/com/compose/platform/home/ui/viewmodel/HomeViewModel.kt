package com.compose.platform.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.business.common.model.Failure
import com.compose.business.common.model.Success
import com.compose.business.home.HomeUsesCaseInterface
import com.compose.platform.home.factory.HomeFactory
import com.compose.platform.home.model.HomeViewState
import com.compose.platform.home.model.OnPause
import com.compose.platform.home.model.OnRefresh
import com.compose.platform.home.model.OnResume
import com.compose.platform.home.model.OnStart
import com.compose.platform.home.model.OnStop
import com.compose.platform.home.model.ViewModelAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUsesCase: HomeUsesCaseInterface,
    private val homeFactory: HomeFactory
) : ViewModel() {

    init {
        sendAction(OnStart)
    }

    private val _uiState = MutableStateFlow(HomeViewState())
    val uiState: StateFlow<HomeViewState> = _uiState.asStateFlow()

    private var fetchHomeJob: Job? = null

    fun sendAction(action: ViewModelAction) {
        when (action) {
            OnStart -> {
                fetchHomeData()
            }
            OnResume -> {}
            OnPause -> {}
            OnStop -> {}
            OnRefresh -> {
                _uiState.update { currentStats ->
                    currentStats.copy(
                        isRefreshing = true
                    )
                }
                fetchHomeData()
            }
        }
    }

    private fun fetchHomeData() {
        fetchHomeJob?.cancel()
        fetchHomeJob = viewModelScope.launch(Dispatchers.IO) {
            when (val response = homeUsesCase.fetchHomeData()) {
                is Success -> {
                    val homeViewElements = homeFactory.generateViewElements(response.result)
                    _uiState.update { currentState ->
                        currentState.copy(
                            cryptoHomeViewElements = homeViewElements,
                            error = null,
                            isRefreshing = false
                        )
                    }
                }
                is Failure -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            cryptoHomeViewElements = emptyList(),
                            error = response.result,
                            isRefreshing = false
                        )
                    }
                }
            }
        }
    }
}