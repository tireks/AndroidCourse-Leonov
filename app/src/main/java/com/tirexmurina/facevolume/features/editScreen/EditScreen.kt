package com.tirexmurina.facevolume.features.editScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tirexmurina.facevolume.features.infoScreen.InfoScreenViewModel

@Composable
fun EditScreen(
    viewModel: EditScreenViewModel = hiltViewModel(),
    onBackClick:() -> Unit,
    contactId : Long?
){
    /*val viewState by viewModel.uiState.collectAsState()

    when (viewState){

    }*/
}