package com.example.erteklerqosqlarhmtaqmaqlar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Maching
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.OnlineTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test

@Database(entities = [Ertek::class, Qosiq::class, Taqmaq::class, Test::class, Maching::class, OnlineTest::class, QosiqTest::class], version = 5)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao(): MainDao
} 