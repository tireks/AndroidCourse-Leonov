package com.tirexmurina.facevolume.features.mainScreen

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

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit
){
    val viewState by viewModel.uiState.collectAsState()

    when (viewState){
        is MainScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is MainScreenState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (viewState as MainScreenState.Error).msg)
            }
        }

        MainScreenState.Initial -> viewModel.getContacts()

        is MainScreenState.Success -> {
            MainScreenContent(
                onItemClick = onItemClick,
                onSettingsClick = onSettingsClick,
                onSearchClick = onSearchClick,
                onAddClick = onAddClick,
                (viewState as MainScreenState.Success).contactList
            )
        }
    }
}