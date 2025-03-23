package com.tirexmurina.facevolume.shared.data.remote.model


data class RandomContactResponse(
    val results: List<RandomContact>
)

data class RandomContact(
    val name: RandomContactName,
    val email: String,
    val phone: String,
    val location: RandomContactLocation,
    val picture: RandomContactPicture
)

data class RandomContactName(
    val first: String,
    val last: String
)

data class RandomContactLocation(
    val street: RandomContactStreet,
    val city: String,
    val country: String
)

data class RandomContactStreet(
    val number: Int,
    val name: String
)

data class RandomContactPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)