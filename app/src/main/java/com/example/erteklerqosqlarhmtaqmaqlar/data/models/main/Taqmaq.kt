package com.example.erteklerqosqlarhmtaqmaqlar.data.models.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("taqmaqlar")
data class Taqmaq(
    @PrimaryKey
    var id: Int,
    var name: String,
    var image: String,
    var text: String,
    var audio: String,
    var is_saved: Int
)
