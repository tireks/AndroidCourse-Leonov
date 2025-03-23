package com.tirexmurina.facevolume.shared.domain.repository

import com.tirexmurina.facevolume.shared.domain.entity.Contact

interface ContactRepository {

    suspend fun getContacts() : List<Contact>

    suspend fun getContactById(id : Long) : Contact

    suspend fun updateContact(contact: Contact)

}