package com.compose.composecrypto.app.di

import com.compose.platform.home.factory.HomeFactory
import com.compose.platform.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    factory { HomeFactory() }
    viewModel { HomeViewModel(homeUsesCase = get(), homeFactory = get()) }
}