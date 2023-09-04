package com.compose.platform.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.platform.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartComposableApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the route of the current screen
    val route = backStackEntry?.destination?.route
    val currentScreen = when {
        route?.startsWith(CryptoScreen.Detail.name) == true -> {
            navController.currentBackStackEntry?.arguments?.getString(CRYPTO_DETAIL_ARGUMENT_KEY)
                ?: stringResource(id = R.string.page_detail_name)
        }
        else -> backStackEntry?.destination?.route ?: stringResource(id = R.string.app_name)
    }

    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = currentScreen) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                                id = R.string.back
                            )
                        )
                    }
                }
            })
    }, content = { innerPadding ->
        AppNavHost(navController = navController, innerPadding)
    })
}