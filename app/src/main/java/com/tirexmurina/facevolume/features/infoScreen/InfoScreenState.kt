package com.tirexmurina.facevolume.features.infoScreen

import com.tirexmurina.facevolume.features.mainScreen.MainScreenState
import com.tirexmurina.facevolume.shared.domain.entity.Contact

sealed interface InfoScreenState {
    data object Initial : InfoScreenState

    data object Loading : InfoScreenState

    data class Success(val contact: Contact) : InfoScreenState

    data class Error(val msg: String) : InfoScreenState
}