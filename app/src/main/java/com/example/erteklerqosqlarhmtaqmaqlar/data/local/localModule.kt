package com.example.erteklerqosqlarhmtaqmaqlar.data.local

import androidx.room.Room
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MainDataBase::class.java, "database-name"
        ).build()
    }

    single { get<MainDataBase>().getDao() }
}