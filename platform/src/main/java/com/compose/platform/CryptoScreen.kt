package com.compose.platform

import androidx.annotation.StringRes

/**
 * enum values that represent the screens in the app
 */
enum class CryptoScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Detail(title = R.string.page_detail_name)
}
