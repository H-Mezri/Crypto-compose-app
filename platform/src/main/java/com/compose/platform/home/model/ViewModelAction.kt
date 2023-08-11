package com.compose.platform.home.model

sealed class ViewModelAction
object OnStart : ViewModelAction()
object OnResume : ViewModelAction()
object OnPause : ViewModelAction()
object OnStop : ViewModelAction()
object OnRefresh : ViewModelAction()