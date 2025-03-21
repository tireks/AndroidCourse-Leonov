package com.tirexmurina.facevolume.features.infoScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tirexmurina.facevolume.shared.domain.entity.Contact

@Composable
fun InfoScreen(
    viewModel: InfoScreenViewModel = hiltViewModel(),
    onBackClick:() -> Unit,
    onEditClick: (Long) -> Unit,
    contactId: Long?
){
    /*val viewState by viewModel.uiState.collectAsState()

    when (viewState){

    }*/
}