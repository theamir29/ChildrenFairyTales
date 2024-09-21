package com.example.erteklerqosqlarhmtaqmaqlar.di

import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek.GetAllErtekLikeUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek.GetAllErtekLikeUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek.GetAllErtekUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek.GetAllErtekUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallmaching.GetRandomMachingUseCase

import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq.GetAllQosiqLikeUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq.GetAllQosiqLikeUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq.GetAllQosiqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq.GetAllQosiqUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq.GetAllTaqmaqLikeUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq.GetAllTaqmaqLikeUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq.GetAllTaqmaqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq.GetAllTaqmaqUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidertek.GetByIdErtekUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidertek.GetByIdErtekUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidqosiq.GetByIdQosiqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidqosiq.GetByIdQosiqUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidtaqmaq.GetByIdTaqmaqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidtaqmaq.GetByIdTaqmaqUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeErtek.LikeErtekUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeErtek.LikeErtekUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeTaqmaq.LikeTaqmaqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeTaqmaq.LikeTaqmaqUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeqosiq.LikeQosiqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeqosiq.LikeQosiqUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getQosiqTestById.GetQosiqTestByIdUserCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getQosiqTestById.GetQosiqTestByIdUserCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getallQosiqTest.GetAllQosiqTestUserCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getallQosiqTest.GetAllQosiqTestUserCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getalltests.GetAllTestsUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getalltests.GetAllTestsUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.gettestbyid.GetTestByIdUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.gettestbyid.GetTestByIdUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.alltests.AllTestsUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.alltests.AllTestsUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.getFeedbacks.GetFeedbacksUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.getFeedbacks.GetFeedbacksUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendaudio.SendAudioUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendaudio.SendAudioUseCaseImpl
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendnotification.SendNotificationUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendnotification.SendNotificationUseCaseImpl
import org.koin.dsl.module
val domainModule = module {

    factory<GetAllErtekUseCase> {
        GetAllErtekUseCaseImpl(
            mainRepository = get()
        )
    }

    factory<GetAllQosiqUseCase> {
        GetAllQosiqUseCaseImpl(
            mainRepository = get()
        )
    }

    factory<GetAllTaqmaqUseCase> {
        GetAllTaqmaqUseCaseImpl(
            mainRepository = get()
        )
    }

    factory<LikeQosiqUseCase> {
        LikeQosiqUseCaseImpl(
            mainRepository = get()
        )
    }

    factory<LikeErtekUseCase> {
        LikeErtekUseCaseImpl(
            mainRepository = get()
        )
    }

    factory<LikeTaqmaqUseCase> {
        LikeTaqmaqUseCaseImpl(
            mainRepository = get()
        )
    }

    factory<GetFeedbacksUseCase> {
        GetFeedbacksUseCaseImpl(repository = get())
    }

    factory<GetAllTestsUseCase> {
        GetAllTestsUseCaseImpl(mainRepository = get())
    }

    factory<GetQosiqTestByIdUserCase> {
        GetQosiqTestByIdUserCaseImpl(mainRepository = get())
    }

    factory<GetAllErtekLikeUseCase> {
        GetAllErtekLikeUseCaseImpl(mainRepository = get())
    }

    factory<GetAllQosiqLikeUseCase> {
        GetAllQosiqLikeUseCaseImpl(mainRepository = get())
    }

    factory<GetAllTaqmaqLikeUseCase> {
        GetAllTaqmaqLikeUseCaseImpl(mainRepository = get())
    }

    factory<GetTestByIdUseCase> {
        GetTestByIdUseCaseImpl(mainRepository = get())
    }

    factory<GetAllQosiqTestUserCase> {
        GetAllQosiqTestUserCaseImpl(mainRepository = get())
    }


    factory<SendNotificationUseCase> {
        SendNotificationUseCaseImpl(networkRepository = get())
    }

    factory<GetByIdErtekUseCase> {
        GetByIdErtekUseCaseImpl(mainRepository = get())
    }

    factory<SendAudioUseCase> {
        SendAudioUseCaseImpl(networkRepository = get())
    }

    factory<GetByIdQosiqUseCase> {
        GetByIdQosiqUseCaseImpl(mainRepository = get())
    }

    factory<GetByIdTaqmaqUseCase> {
        GetByIdTaqmaqUseCaseImpl(mainRepository = get())
    }

    factory<AllTestsUseCase> {
        AllTestsUseCaseImpl(networkRepository = get())
    }


    factory<GetRandomMachingUseCase> {
        GetRandomMachingUseCase(mainRepository = get())
    }

}
