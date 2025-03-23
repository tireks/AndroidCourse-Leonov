package com.tirexmurina.facevolume.features.searchScreen

import com.tirexmurina.facevolume.shared.domain.entity.Contact

sealed interface SearchScreenState {

    data object Initial : SearchScreenState

    data object Loading : SearchScreenState

    data class SearchResult(val contactList : List<Contact>) : SearchScreenState

    data class Error(val msg: String) : SearchScreenState

}