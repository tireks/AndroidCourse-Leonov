package com.tirexmurina.facevolume.features.editScreen

import com.tirexmurina.facevolume.shared.domain.entity.Contact

sealed interface EditScreenState {

    data object Initial : EditScreenState

    data object Loading : EditScreenState

    data class Success(val contact: Contact?) : EditScreenState

    data class Error(val msg: String) : EditScreenState

}