package com.example.erteklerqosqlarhmtaqmaqlar.data.models.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ertekler")
data class Ertek(
    @PrimaryKey
    var id: Int,
    var name: String,
    var image: String,
    var text: String,
    var video: String,
    var is_saved: Int
)
