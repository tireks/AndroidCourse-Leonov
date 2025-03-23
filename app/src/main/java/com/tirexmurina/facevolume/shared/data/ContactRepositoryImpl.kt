package com.tirexmurina.facevolume.shared.data

import com.tirexmurina.facevolume.shared.data.local.FakeContacts
import com.tirexmurina.facevolume.shared.domain.entity.Contact
import com.tirexmurina.facevolume.shared.domain.repository.ContactRepository
import com.tirexmurina.facevolume.shared.util.ContactListCorruptedException
import com.tirexmurina.facevolume.shared.util.ContactSavingException
import com.tirexmurina.facevolume.shared.util.NoSuchElementException
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val fakeContacts : FakeContacts
) : ContactRepository {
    override suspend fun getContacts(): List<Contact> {
        return try {
            fakeContacts.contacts
        } catch (e : Exception) {
            throw ContactListCorruptedException("Contacts not found")
        }
    }

    override suspend fun getContactById(id: Long): Contact {
        return try {
            fakeContacts.contacts.single { it.id == id }
        } catch (e : Exception) {
            throw NoSuchElementException("Contact not found")
        }
    }

    override suspend fun updateContact(contact: Contact) {
        try {
            if (contact.id == -1L) {
                val newId = (fakeContacts.contacts.maxOfOrNull { it.id } ?: 0L) + 1
                val newContact = contact.copy(id = newId)
                fakeContacts.contacts.add(newContact)
            } else {
                val index = fakeContacts.contacts.indexOfFirst { it.id == contact.id }
                if (index != -1) {
                    fakeContacts.contacts[index] = contact
                } else {
                    throw ContactSavingException("Failed to save contact")
                }
            }
        } catch ( e : Exception ) {
            throw ContactSavingException("Failed to save contact")
        }
    }



}