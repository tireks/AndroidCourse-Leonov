package com.tirexmurina.facevolume.shared.data.local.model

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