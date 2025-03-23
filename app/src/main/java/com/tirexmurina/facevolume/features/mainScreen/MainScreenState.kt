package com.tirexmurina.facevolume.features.mainScreen

import com.tirexmurina.facevolume.shared.domain.entity.Contact

sealed interface MainScreenState {

    data object Initial : MainScreenState

    data object Loading : MainScreenState

    data class Success(val contactList : List<Contact>) : MainScreenState

    data class Error(val msg: String) : MainScreenState

}