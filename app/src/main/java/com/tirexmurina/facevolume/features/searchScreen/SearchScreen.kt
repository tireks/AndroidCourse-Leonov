package com.tirexmurina.facevolume.features.searchScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit,
    onBackClick:() -> Unit,
){
    /*val viewState by viewModel.uiState.collectAsState()

    when (viewState){

    }*/
}