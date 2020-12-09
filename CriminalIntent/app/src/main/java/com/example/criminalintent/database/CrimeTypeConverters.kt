package com.example.criminalintent.database

import androidx.room.TypeConverter
import java.util.*

/** Room can store primitive types, since it's SQLite under the hood. So for our Crime entity
 * we need to change the UUID and Date to types that Room can handle, as well as change the data
 * we get from the DB back to the types we expect.
 *
 * We need to tell the database (In this case, CrimeDatabase.kt) that it can find converters here
 */

class CrimeTypeConverters {

    // This goes from a date (Crime) to a long (Room DB)
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid.toString()
    }
}