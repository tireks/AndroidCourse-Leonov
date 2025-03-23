package com.tirexmurina.facevolume.shared.util

import com.tirexmurina.facevolume.shared.data.local.model.ContactLocalDatabaseModel
import com.tirexmurina.facevolume.shared.data.local.model.LocationLocalDatabaseModel
import com.tirexmurina.facevolume.shared.data.remote.model.RandomContact
import com.tirexmurina.facevolume.shared.domain.entity.Contact

fun Contact.toLocalDatabaseModel(): ContactLocalDatabaseModel {
    return ContactLocalDatabaseModel(
        id = if (this.id == -1L) 0 else this.id,
        name = this.name,
        pic = this.pic,
        phone = this.phone,
        email = this.email,
        note = this.note,
        location = this.location?.let { location ->
            LocationLocalDatabaseModel(
                country = location.country,
                city = location.city,
                address = location.address,
                timezone = location.timezone
            )
        }
    )
}

fun ContactLocalDatabaseModel.toDomainModel(): Contact {
    return Contact(
        id = this.id,
        name = this.name,
        pic = this.pic,
        phone = this.phone,
        email = this.email,
        note = this.note,
        location = this.location?.let { location ->
            Contact.Location(
                country = location.country,
                city = location.city,
                address = location.address,
                timezone = location.timezone
            )
        }
    )
}

fun RandomContact.toDomainModel(): Contact {
    return Contact(
        id = -1L,
        name = "${this.name.first} ${this.name.last}",
        pic = this.picture.large,
        phone = this.phone,
        email = this.email,
        location = Contact.Location(
            country = this.location.country,
            city = this.location.city,
            address = "${this.location.street.number} ${this.location.street.name}",
            timezone = Contact.Timezone.entries.toTypedArray().random()
        ),
        note = null
    )
}