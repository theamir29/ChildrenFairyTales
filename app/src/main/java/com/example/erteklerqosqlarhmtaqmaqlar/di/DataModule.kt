package com.example.erteklerqosqlarhmtaqmaqlar.di

import androidx.room.Room
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDao
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDataBase
import com.example.erteklerqosqlarhmtaqmaqlar.data.repository.MainRepositoryImpl
import com.example.erteklerqosqlarhmtaqmaqlar.data.repository.NetworkRepositoryImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository
import com.example.erteklerqosqlarhmtaqmaqlar.domain.NetworkRepository
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallmaching.GetRandomMachingUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {
    single<MainRepository> {
        MainRepositoryImpl(
            dao = get()
        )
    }

    single<NetworkRepository> {
        NetworkRepositoryImpl(
            fcmApi = get(),
            networkApi = get()
        )
    }

    single<MainDataBase> {
        Room.databaseBuilder(
            androidContext(),
            MainDataBase::class.java,
            "main.db"
        )
            .allowMainThreadQueries()
            .createFromAsset("main.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<MainDao> {
        get<MainDataBase>().getDao()
    }

    //single { MainRepository() } // Замените на ваш реальный репозиторий
    single { GetRandomMachingUseCase(get()) }
}
