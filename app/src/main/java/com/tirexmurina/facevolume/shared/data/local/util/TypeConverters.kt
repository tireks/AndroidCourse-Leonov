package com.tirexmurina.facevolume.shared.data.local.util

import androidx.room.TypeConverter
import com.tirexmurina.facevolume.shared.domain.entity.Contact

class Converters {
    @TypeConverter
    fun fromTimezone(timezone: Contact.Timezone?): String? {
        return timezone?.name
    }

    @TypeConverter
    fun toTimezone(timezoneName: String?): Contact.Timezone? {
        return if (timezoneName == null) null else Contact.Timezone.valueOf(timezoneName)
    }
}