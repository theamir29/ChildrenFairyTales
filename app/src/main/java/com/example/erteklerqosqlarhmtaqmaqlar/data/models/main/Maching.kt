package com.example.erteklerqosqlarhmtaqmaqlar.data.models.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maching")
data class Maching(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "maching") val maching: String
)
