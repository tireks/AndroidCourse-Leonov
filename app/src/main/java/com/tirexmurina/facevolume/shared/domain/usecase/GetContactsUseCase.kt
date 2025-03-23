package com.tirexmurina.facevolume.shared.domain.usecase

import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactRepository
){
    suspend operator fun invoke() : List<Contact> = repository.getContacts()
}