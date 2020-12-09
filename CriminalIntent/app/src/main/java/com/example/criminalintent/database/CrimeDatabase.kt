package com.example.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.criminalintent.Crime

/** This serves as the database class for our app. We pass an entity, Crime which will be a table for us to use.
 * We also specify the class where the type converters are, where we need to convert a type we use that
 * room cannot store in the SQLite database
 */

@Database(entities= [Crime:class], version=1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase: RoomDatabase() {

    // Don't forget to let our databse know where we put our DAO. When the db is created, we will have
    // concrete implementation of our DAO we can use
    abstract fun crimeDao(): CrimeDao

}