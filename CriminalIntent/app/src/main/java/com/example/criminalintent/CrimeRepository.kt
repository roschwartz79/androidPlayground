package com.example.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.criminalintent.database.CrimeDao
import com.example.criminalintent.database.CrimeDatabase
import java.util.*

/** This repository is responsible for how to fetch and store a set of data. The UI will
 * call the repository which will deal with the rest, as the UI doesn't care about how the data is fetched.
 * Repositories are singletons, there can only ever be one instance of it.
 *
 * We want to set this up as soon as the application is ready. We do this in CriminalIntentApplication,
 * which overrides the Application onCreate method to create a new instance of the repo
 */

private const val DATABASE_NAME = "crime-database";


class CrimeRepository private constructor(context:Context){

    // Here we have a concrete implementation of the CrimeDatabase, which is specified as abstract
    // The app context will live longer than any of the activities, but you could pass an instance of
    // an activities context.... I think
    private val database : CrimeDatabase = Room.databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME).build()

    // Here is an instance of crimeDao we will use to access the Database
    private val crimeDao: CrimeDao = database.crimeDao()




    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)





    // These functions make it a singleton. The constructor for this class is private so there can't
    // be any rogue components and create new instances of the repo class.
    companion object{
        private var INSTANCE: CrimeRepository? = null

        // Here we create an instance of the Repo, only if its null.
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        // here we can access the repository
        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized.")
            }
    }
}