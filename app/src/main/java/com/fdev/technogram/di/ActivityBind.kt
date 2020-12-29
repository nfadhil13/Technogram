package com.fdev.technogram.di

import com.fdev.technogram.datasource.network.service.NewsApiService
import com.fdev.technogram.repository.news.NewsRepository
import com.fdev.technogram.repository.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityBind {

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository


}