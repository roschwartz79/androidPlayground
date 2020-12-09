package com.example.criminalintent

import androidx.lifecycle.ViewModel

private const val TAG = "CrimeListViewModel"

class CrimeListViewModel: ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    val crimesListLiveData = crimeRepository.getCrimes()

}