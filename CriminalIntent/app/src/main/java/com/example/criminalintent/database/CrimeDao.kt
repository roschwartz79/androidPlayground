package com.example.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.criminalintent.Crime
import java.util.*

/** DAO = Data Access Object
 * This interface serves to hold functions we use to look through our database and use it
 * We annotate it with @Dao and let the CrimeDatabase know that it can use the DAO here
 *
 * Why use LiveData here? We can return LiveData objects that contain our actual data, which will
 * perform the queries on a background thread, then when it is complete the main thread will let
 * the listeners know the data is ready
 */

@Dao
interface CrimeDao {

    // First we write a sql statement that will get all of the crimes
    @Query("Select * from crime")
    fun getCrimes(): LiveData<List<Crime>>

    @Query("Select * from crime where id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
}