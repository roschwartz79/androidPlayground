package com.example.criminalintent

import java.util.*

data class Crime(var title: String = "", val id: UUID = UUID.randomUUID(), var date: Date = Date(), var isSolved: Boolean = false){

}