package com.tirexmurina.facevolume.shared.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tirexmurina.facevolume.shared.domain.entity.Contact

@Entity(tableName = "contacts")
data class ContactLocalDatabaseModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val pic: String?,
    val phone: String?,
    val email: String?,
    val note: String?,
    @Embedded(prefix = "location_")
    val location: LocationLocalDatabaseModel?
)

data class LocationLocalDatabaseModel(
    val country: String?,
    val city: String?,
    val address: String?,
    val timezone: Contact.Timezone?
)
