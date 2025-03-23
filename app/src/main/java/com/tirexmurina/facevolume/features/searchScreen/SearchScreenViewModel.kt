package com.tirexmurina.facevolume.features.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.shared.domain.usecase.GetContactsUseCase
import com.tirexmurina.facevolume.shared.util.ContactListCorruptedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchScreenState>(SearchScreenState.Initial)
    val uiState: StateFlow<SearchScreenState> = _uiState.asStateFlow()
    private val contactList : MutableList<Contact> = mutableListOf()
    private var searchJob : Job? = null

    fun startScreen() {
        _uiState.value = SearchScreenState.SearchResult(listOf())
    }

    fun searchContacts(query: String) {
        if (query.isEmpty()) {
            _uiState.value = SearchScreenState.SearchResult(listOf())
            return
        }
        _uiState.value = SearchScreenState.Loading
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (contactList.isEmpty()) {
                try {
                    val result = getContactsUseCase()
                    contactList.addAll(result)

                } catch (e : Exception) {
                    if (e !is CancellationException) {
                        errorHandler(e)
                    }
                }
            }
            try {
                val result = performSearch(query)
                _uiState.value = SearchScreenState.SearchResult(result)
            } catch (e : Exception) {
                if (e !is CancellationException) {
                    errorHandler(e)
                }
            }
        }
    }

    /**
     * В целом, можно это наверное и в репо перенести, там список оригинальный получать,
     * там и "профильтровывать"
     * */
    private fun performSearch(query : String): List<Contact> {
        val lowerQuery = query.lowercase()

        val result = contactList.mapNotNull { contact ->
            val nameMatches = contact.name.lowercase().contains(lowerQuery)
            val emailMatch = contact.email?.takeIf { it.lowercase().contains(lowerQuery) }
            val phoneMatch = contact.phone?.takeIf { it.lowercase().contains(lowerQuery) }
            val noteMatch = contact.note?.takeIf { it.lowercase().contains(lowerQuery) }

            val locationMatch = contact.location?.let { loc ->
                val countryMatch = loc.country?.takeIf { it.lowercase().contains(lowerQuery) }
                val cityMatch = loc.city?.takeIf { it.lowercase().contains(lowerQuery) }
                val addressMatch = loc.address?.takeIf { it.lowercase().contains(lowerQuery) }
                val timezoneMatch = loc.timezone?.let { tz ->
                    if (tz.zoneName.lowercase().contains(lowerQuery)) tz else null
                }
                if (countryMatch != null || cityMatch != null || addressMatch != null || timezoneMatch != null) {
                    Contact.Location(
                        country = countryMatch,
                        city = cityMatch,
                        address = addressMatch,
                        timezone = timezoneMatch
                    )
                } else null
            }

            if (nameMatches || emailMatch != null || phoneMatch != null || noteMatch != null || locationMatch != null) {
                contact.copy(
                    email = emailMatch,
                    phone = phoneMatch,
                    note = noteMatch,
                    location = locationMatch
                )
            } else null
        }

        return result
    }

    private fun errorHandler(exception: Exception) {
        when(exception) {
            is ContactListCorruptedException -> {
                _uiState.value = SearchScreenState.Error("Возникла проблема с получением списка контактов")
            }

            else -> {
                _uiState.value = SearchScreenState.Error("Неизвестная ошибка")
            }
        }
    }

}