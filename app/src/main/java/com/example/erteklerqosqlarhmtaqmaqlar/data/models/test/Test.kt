package com.example.erteklerqosqlarhmtaqmaqlar.data.models.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "testler")
data class Test(
    @PrimaryKey
    var id: Int,
    var image: String?,
    var audio: String,
    var answers: String
)
