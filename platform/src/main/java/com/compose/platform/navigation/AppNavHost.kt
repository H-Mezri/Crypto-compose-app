package com.compose.platform.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.compose.platform.detail.ui.CreateCryptoDetailView
import com.compose.platform.home.ui.CreateHomeView

const val CRYPTO_DETAIL_ARGUMENT_KEY = "cryptoId"

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = CryptoScreen.Home.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = CryptoScreen.Home.name) {
            CreateHomeView { cryptoId ->
                navController.navigate("${CryptoScreen.Detail.name}/${cryptoId}")
            }
        }
        composable(route = "${CryptoScreen.Detail.name}/{$CRYPTO_DETAIL_ARGUMENT_KEY}", arguments = listOf(
            navArgument(name = CRYPTO_DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            }
        )) { backStackEntry ->
            val cryptoId = backStackEntry.arguments?.getString(CRYPTO_DETAIL_ARGUMENT_KEY) ?: ""
            CreateCryptoDetailView(cryptoId)
        }
    }
}