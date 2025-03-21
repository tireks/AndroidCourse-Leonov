package com.tirexmurina.facevolume.features.settingsScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = hiltViewModel(),
    onBackClick:() -> Unit,
    onModeSwitchClick:() -> Unit
){
    /*val viewState by viewModel.uiState.collectAsState()

    when (viewState){

    }*/
}