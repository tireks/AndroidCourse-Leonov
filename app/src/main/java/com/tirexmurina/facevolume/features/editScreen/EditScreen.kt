package com.tirexmurina.facevolume.features.editScreen

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
import com.tirexmurina.facevolume.features.infoScreen.InfoScreenState
import com.tirexmurina.facevolume.features.infoScreen.InfoScreenViewModel

@Composable
fun EditScreen(
    viewModel: EditScreenViewModel = hiltViewModel(),
    onBackClick:() -> Unit,
    contactId : Long?
){
    val viewState by viewModel.uiState.collectAsState()

    when (viewState){
        EditScreenState.Initial -> viewModel.getContact(contactId)

        EditScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is EditScreenState.Success -> {
            EditScreenContent(
                onBackClick = onBackClick,
                contact = (viewState as EditScreenState.Success).contact,
                onSave = { viewModel.saveContact(it) }
            )
        }

        is EditScreenState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (viewState as EditScreenState.Error).msg)
            }
        }
    }
}