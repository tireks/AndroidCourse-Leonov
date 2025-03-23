package com.tirexmurina.facevolume.features.infoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tirexmurina.facevolume.features.mainScreen.MainScreenState
import com.tirexmurina.facevolume.shared.domain.entity.Contact

@Composable
fun InfoScreen(
    viewModel: InfoScreenViewModel = hiltViewModel(),
    onBackClick:() -> Unit,
    onEditClick: (Long) -> Unit,
    contactId: Long?
){
    val viewState by viewModel.uiState.collectAsState()

    when (viewState){
        InfoScreenState.Initial -> viewModel.getContact(contactId)
        
        InfoScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        
        is InfoScreenState.Success -> {
            InfoScreenContent(
                onBackClick = onBackClick,
                onEditClick = onEditClick,
                onDialerClick = { /*TODO*/ },
                onEmailClick = { /*TODO*/ },
                contact = (viewState as InfoScreenState.Success).contact
            )
        }
        
        is InfoScreenState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (viewState as InfoScreenState.Error).msg)
            }
        }
    }
}