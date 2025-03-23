package com.tirexmurina.facevolume.features.searchScreen

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
import androidx.room.util.query
import com.tirexmurina.facevolume.features.mainScreen.MainScreenState

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit,
    onBackClick:() -> Unit,
){
    val viewState by viewModel.uiState.collectAsState()

    when (viewState){
        SearchScreenState.Initial -> viewModel.startScreen()

        SearchScreenState.Loading -> {
            SearchScreenContent(
                onItemClick = onItemClick,
                onBackClick = onBackClick,
                onQueryChange = { query -> viewModel.searchContacts(query) },
                contacts = listOf(),
                onLoading = true
            )
        }

        is SearchScreenState.SearchResult -> {
            SearchScreenContent(
                onItemClick = onItemClick,
                onBackClick = onBackClick,
                onQueryChange = { query -> viewModel.searchContacts(query) },
                contacts = (viewState as SearchScreenState.SearchResult).contactList,
                onLoading = false
            )
        }

        is SearchScreenState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (viewState as SearchScreenState.Error).msg)
            }
        }
    }
}