package com.example.openapisample.di

import com.example.openapisample.data.AuthDataSource
import com.example.openapisample.data.TweetDataSource
import com.example.openapisample.data.TwitterMockRepository
import com.example.openapisample.data.TwitterRepository
import com.example.openapisample.interactor.MainInteractor
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
        MainInteractor(repo = get(named(DINames.TwitterMockRepository.name)))
    }
    viewModel {
        MainViewModel(
            interactor = get()
        )
    }
}

val tweetDetailSceneModule = module {

}

val appModules = listOf(
    dataModule, mainSceneModule, tweetDetailSceneModule
)
