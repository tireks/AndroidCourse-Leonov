package com.tirexmurina.facevolume.features.editScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.facevolume.features.infoScreen.InfoScreenState
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.shared.domain.usecase.GetContactByIdUseCase
import com.tirexmurina.facevolume.shared.domain.usecase.GetRandomContactUseCase
import com.tirexmurina.facevolume.shared.domain.usecase.UpdateContactUseCase
import com.tirexmurina.facevolume.shared.util.ContactSavingException
import com.tirexmurina.facevolume.shared.util.NoSuchElementException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    private val getRandomContactUseCase: GetRandomContactUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<EditScreenState>(EditScreenState.Initial)
    val uiState: StateFlow<EditScreenState> = _uiState.asStateFlow()

    fun getContact(id : Long?) {
        if (id == null) {
            errorHandler(NoSuchElementException())
            return
        }
        if (id < 0) {
            _uiState.value = EditScreenState.Success(null)
        } else {
            viewModelScope.launch {
                try {
                    val result = getContactByIdUseCase(id)
                    _uiState.value = EditScreenState.Success(result)
                } catch (e : Exception) {
                    errorHandler(e)
                }
            }
        }
    }

    fun saveContact(contact: Contact) {
        viewModelScope.launch {
            try {
                updateContactUseCase(contact)
            } catch (e : Exception) {
                errorHandler(e)
            }
        }
    }

    fun getRandomContact() {
        _uiState.value = EditScreenState.Loading
        viewModelScope.launch {
            try {
                val result = getRandomContactUseCase()
                _uiState.value = EditScreenState.Success(result)
            } catch ( e : Exception) {
                errorHandler(e)
            }
        }
    }

    private fun errorHandler(exception: Exception) {
        when(exception) {
            is NoSuchElementException -> {
                _uiState.value = EditScreenState.Error("Контакт не найден")
            }

            is ContactSavingException -> {
                _uiState.value = EditScreenState.Error("Ошибка при сохранении контакта")
            }

            else -> {
                _uiState.value = EditScreenState.Error("Неизвестная ошибка")
            }
        }
    }

}