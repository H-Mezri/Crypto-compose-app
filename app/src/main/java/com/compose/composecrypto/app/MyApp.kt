package com.compose.composecrypto.app

import android.app.Application
import com.compose.composecrypto.app.di.businessModule
import com.compose.composecrypto.app.di.dataModule
import com.compose.composecrypto.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(dataModule, businessModule, viewModule))
        }
    }
}