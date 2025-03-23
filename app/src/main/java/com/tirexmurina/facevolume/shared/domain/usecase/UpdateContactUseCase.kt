package com.tirexmurina.facevolume.shared.domain.usecase

import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor(
    private val repository: ContactRepository
){
    suspend operator fun invoke(contact : Contact) = repository.updateContact(contact)
}