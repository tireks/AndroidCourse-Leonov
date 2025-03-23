package com.tirexmurina.facevolume.features.editScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tirexmurina.facevolume.shared.domain.entity.Contact

class EditFormState(initialContact: Contact?) {
    private val originalContact: Contact? = initialContact

    private val initialName = initialContact?.name ?: ""
    private val initialEmail = initialContact?.email
    private val initialPhone = initialContact?.phone
    private val initialCountry = initialContact?.location?.country
    private val initialCity = initialContact?.location?.city
    private val initialAddress = initialContact?.location?.address
    private val initialTimezone = initialContact?.location?.timezone
    private val initialNotes = initialContact?.note
    private val initialPic = initialContact?.pic

    var name by mutableStateOf(initialName)
    var email by mutableStateOf(initialEmail)
    var phone by mutableStateOf(initialPhone)
    var country by mutableStateOf(initialCountry)
    var city by mutableStateOf(initialCity)
    var address by mutableStateOf(initialAddress)
    var selectedTimezone by mutableStateOf(initialTimezone)
    var notes by mutableStateOf(initialNotes)
    var pic by mutableStateOf(initialPic)
    var nameError by mutableStateOf<String?>(null)

    val unsavedChanges: Boolean
        get() = name != initialName ||
                email != initialEmail ||
                phone != initialPhone ||
                country != initialCountry ||
                city != initialCity ||
                address != initialAddress ||
                (selectedTimezone?.zoneName ?: "") != (initialTimezone?.zoneName ?: "") ||
                notes != initialNotes ||
                pic != initialPic

    fun save(): Contact? {
        if (name.isBlank()) {
            nameError = "Имя обязательно"
            return null
        }
        return Contact(
            id = originalContact?.id ?: -1L,
            name = name,
            email = email,
            phone = phone,
            note = notes,
            pic = pic,
            location = Contact.Location(
                country = country,
                city = city,
                address = address,
                timezone = selectedTimezone
            )
        )
    }
}

