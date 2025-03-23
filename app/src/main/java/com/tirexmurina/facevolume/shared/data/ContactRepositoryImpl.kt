package com.tirexmurina.facevolume.shared.data

import com.tirexmurina.facevolume.shared.data.local.ContactDao
import com.tirexmurina.facevolume.shared.data.local.FakeContacts
import com.tirexmurina.facevolume.shared.data.local.model.toDomainModel
import com.tirexmurina.facevolume.shared.data.local.model.toLocalDatabaseModel
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import com.tirexmurina.facevolume.shared.util.ContactListCorruptedException
import com.tirexmurina.facevolume.shared.util.ContactSavingException
import com.tirexmurina.facevolume.shared.util.NoSuchElementException
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
) : ContactRepository {
    override suspend fun getContacts(): List<Contact> {
        return try {
            contactDao.getAllContacts().map { databaseContact -> databaseContact.toDomainModel() }
        } catch (e : Exception) {
            throw ContactListCorruptedException("Contacts not found")
        }
    }

    override suspend fun getContactById(id: Long): Contact {
        return try {
            val result = contactDao.getContactById(id)
            result?.toDomainModel() ?: throw Exception()
        } catch (e : Exception) {
            throw NoSuchElementException("Contact not found")
        }
    }

    override suspend fun updateContact(contact: Contact) {
        try {
            if (contact.id == -1L) {
                contactDao.insertContact(contact.toLocalDatabaseModel())
            } else {
                contactDao.updateContact(contact.toLocalDatabaseModel())
            }
        } catch ( e : Exception ) {
            throw ContactSavingException("Failed to save contact")
        }
    }



}