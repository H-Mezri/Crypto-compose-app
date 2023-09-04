package com.compose.platform.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.compose.platform.R
import com.compose.platform.home.model.CryptoHomeViewElement
import com.compose.platform.home.model.OnRefresh
import com.compose.platform.home.ui.viewmodel.HomeViewModel
import com.compose.platform.common.theme.CryptoTheme
import com.compose.platform.common.theme.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateHomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToCryptoDetail: (cryptoId: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(refreshing = uiState.isRefreshing,
        onRefresh = { viewModel.sendAction(OnRefresh) })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pullRefresh(pullRefreshState)
    ) {
        val viewElements = uiState.cryptoHomeViewElements
        val showEmptyView = viewElements.isEmpty() && uiState.isRefreshing.not()
        if (showEmptyView) {
            CreateEmptyHomeContent()
        } else {
            LazyColumn {
                items(items = viewElements, itemContent = { homeViewElement ->
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CreateEventCardContent(homeViewElement, onNavigateToCryptoDetail)
                    }
                })
            }
            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = uiState.isRefreshing,
                state = pullRefreshState
            )
        }
    }
}

@Composable
fun CreateEmptyHomeContent() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_wallet),
            contentDescription = stringResource(id = R.string.empty_wallet_icon),
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .padding(spacing.space8dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = stringResource(id = R.string.empty_home_page),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CreateEventCardContent(
    crypto: CryptoHomeViewElement, onNavigateToCryptoDetail: (cryptoId: String) -> Unit
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing.space8dp)
            .clickable { onNavigateToCryptoDetail(crypto.title) },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colors.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            GlideImage(
                model = crypto.image,
                contentDescription = stringResource(id = R.string.crypto_logo),
                modifier = Modifier
                    .height(150.dp)
                    .padding(spacing.space0dp, spacing.space24dp)
                    .align(Alignment.Center),
                alpha = 0.2f,
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(spacing.space8dp)
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        model = crypto.logoUrl,
                        contentDescription = stringResource(id = R.string.crypto_logo),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(
                                spacing.space0dp,
                                spacing.space2dp,
                                spacing.space8dp,
                                spacing.space0dp
                            )
                    )
                    Text(
                        text = crypto.title,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = stringResource(id = R.string.price, crypto.currentPrice),
                    modifier = Modifier.align(Alignment.Start),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(spacing.space8dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = stringResource(id = R.string.ath, crypto.ath),
                    modifier = Modifier.wrapContentWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 13.sp
                )
            }
        }
    }
}

// preview tool
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoTheme {
        CreateEventCardContent(
            CryptoHomeViewElement(
                123,
                "btc",
                "BTC",
                "url",
                "url",
                123.3,
                12333.0,
                1,
                1234.0,
                1234.0,
                1234.0,
                23.0,
                21.0,
                1244.0,
                1234.0,
                55555.0,
                123.0,
                "123"
            )
        ) {}
    }
}
