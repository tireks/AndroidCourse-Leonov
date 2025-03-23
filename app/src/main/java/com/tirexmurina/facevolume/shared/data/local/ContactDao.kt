package com.tirexmurina.facevolume.shared.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tirexmurina.facevolume.shared.data.local.model.ContactLocalDatabaseModel

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<ContactLocalDatabaseModel>

    @Query("SELECT * FROM contacts WHERE id = :id LIMIT 1")
    suspend fun getContactById(id: Long): ContactLocalDatabaseModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactLocalDatabaseModel): Long

    @Update
    suspend fun updateContact(contact: ContactLocalDatabaseModel): Int



}