package com.example.openapisample.di

import com.example.openapisample.data.remote.AuthDataSource
import com.example.openapisample.data.remote.TweetDataSource
import com.example.openapisample.data.TwitterMockRepository
import com.example.openapisample.data.TwitterRepository
import com.example.openapisample.presentation.detail.interactor.DetailInteractor
import com.example.openapisample.presentation.main.interactor.MainInteractor
import com.example.openapisample.presentation.detail.viewmodel.DetailViewModel
import com.example.openapisample.presentation.intro.interactor.IntroInteractor
import com.example.openapisample.presentation.intro.viewmodel.IntroViewModel
import com.example.openapisample.presentation.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class DINames {
    TwitterRepository,
    TwitterMockRepository
}

val dataModule = module {
    single { AuthDataSource() }
    single { TweetDataSource() }
    single(named(DINames.TwitterRepository.name)) {
        TwitterRepository(
            authDataSource = get(),
            tweetDataSource = get()
        )
    }
    single(named(DINames.TwitterMockRepository.name)) {
        TwitterMockRepository()
    }
}

val mainSceneModule = module {
    factory {
        MainInteractor(
            repo = get(
                named(
                    DINames.TwitterMockRepository.name
                )
            )
        )
    }
    viewModel {
        MainViewModel(
            interactor = get()
        )
    }
}

val tweetDetailSceneModule = module {
    factory {
        DetailInteractor(
            repo = get(
                named(
                    DINames.TwitterMockRepository.name
                )
            )
        )
    }
    viewModel { (id: Long) ->
        DetailViewModel(
            id = id,
            interactor = get()
        )
    }
}

val introSceneModule = module {
    factory {
        IntroInteractor(
            repo = get(
                named(
                    DINames.TwitterMockRepository.name
                )
            )
        )
    }
    viewModel {
        IntroViewModel(
            interactor = get()
        )
    }
}

val appModules = listOf(
    dataModule, mainSceneModule, tweetDetailSceneModule, introSceneModule
)
