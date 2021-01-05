package com.fdev.technogram.di

import com.fdev.technogram.datasource.network.framework.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object FragmentModule {


    @Provides
    fun provideNewsApi(
        retrofitBuilder : Retrofit.Builder
    ) : NewsApiService {
        return  retrofitBuilder
            .build()
            .create(NewsApiService::class.java)
    }
}