package com.example.erteklerqosqlarhmtaqmaqlar.data.models.test

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "onlineTest")
data class OnlineTest(
    @PrimaryKey
    val id: Int,
    val audioUrl: String,
    val imageUrl: String
)
