package com.example.erteklerqosqlarhmtaqmaqlar.di

import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MainViewModelImpl
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.local.MatchingViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.network.GetFeedbacksViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.network.NetworkViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.presentation.network.NetworkViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModelImpl(
            getAllErtekUseCase = get(),
            getAllQosiqUseCase = get(),
            getAllTaqmaqUseCase = get(),
            likeQosiqUseCase = get(),
            likeErtekUseCase = get(),
            likeTaqmaqUseCase = get(),
            getAllTestsUseCase = get(),
            getAllErtekLikeUseCase = get(),
            getAllQosiqLikeUseCase = get(),
            getAllTaqmaqLikeUseCase = get(),
            getTestByIdUseCase = get(),
            getByIdErtekUseCase = get(),
            getByIdQosiqUseCase = get(),
            getByIdTaqmaqUseCase = get(),
            getAllQosiqTestsUseCase = get(),
            getQosiqTestByIdUserCase =get()
        )
    }

    viewModel<NetworkViewModel> {
        NetworkViewModelImpl(
            sendNotificationUseCase = get(),
            sendAudioUseCase = get(),
            allTestsUseCase = get()
        )
    }

    viewModel<GetFeedbacksViewModel> {
        GetFeedbacksViewModel(useCase = get())
    }

    viewModel { MatchingViewModel(getRandomMachingUseCase = get()) }



}

