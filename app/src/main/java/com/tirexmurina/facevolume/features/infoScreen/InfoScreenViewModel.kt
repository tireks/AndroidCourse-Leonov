package com.tirexmurina.facevolume.features.infoScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.facevolume.shared.domain.usecase.GetContactByIdUseCase
import com.tirexmurina.facevolume.shared.util.NoSuchElementException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoScreenState>(InfoScreenState.Initial)
    val uiState: StateFlow<InfoScreenState> = _uiState.asStateFlow()

    fun getContact(id : Long?) {
        _uiState.value = InfoScreenState.Loading
        if (id == null || id < 0) {
            errorHandler(NoSuchElementException())
        } else {
            viewModelScope.launch {
                try {
                    val result = getContactByIdUseCase(id)
                    _uiState.value = InfoScreenState.Success(result)
                } catch (e : Exception) {
                    errorHandler(e)
                }
            }
        }

    }

    private fun errorHandler(exception: Exception) {
        when(exception) {
            is NoSuchElementException -> {
                _uiState.value = InfoScreenState.Error("Контакт не найден")
            }

            else -> {
                _uiState.value = InfoScreenState.Error("Неизвестная ошибка")
            }
        }
    }
}