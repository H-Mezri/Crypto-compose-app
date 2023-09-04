package com.compose.platform.navigation

import androidx.annotation.StringRes
import com.compose.platform.R

/**
 * enum values that represent the screens in the app
 */
enum class CryptoScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Detail(title = R.string.page_detail_name)
}
