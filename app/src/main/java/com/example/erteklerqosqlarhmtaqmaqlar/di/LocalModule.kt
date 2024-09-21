package com.example.erteklerqosqlarhmtaqmaqlar.di

import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDao
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDataBase
import org.koin.dsl.module

val localModule = module {
    single<MainDao> {
        get<MainDataBase>().getDao()
    }
}