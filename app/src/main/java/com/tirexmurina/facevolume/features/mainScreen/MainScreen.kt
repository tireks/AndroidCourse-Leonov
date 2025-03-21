package com.tirexmurina.facevolume.features.mainScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit
){
    /*val viewState by viewModel.uiState.collectAsState()

    when (viewState){

    }*/
}