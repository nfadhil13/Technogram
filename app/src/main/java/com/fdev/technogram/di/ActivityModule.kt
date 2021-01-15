package com.fdev.technogram.di

import com.fdev.technogram.datasource.network.business.abstraction.CategoryNetworkDataSource
import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
import com.fdev.technogram.datasource.network.business.implementation.CategoryNetworkDataSourceImpl
import com.fdev.technogram.datasource.network.business.implementation.NewsNetworkDataSourceImpl
import com.fdev.technogram.datasource.network.framework.service.CategoryApiService
import com.fdev.technogram.datasource.network.framework.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ActivityModule {


    @ViewModelScoped
    @Provides
    fun provideNewsApi(
        retrofitBuilder : Retrofit.Builder
    ) : NewsApiService {
        return  retrofitBuilder
            .build()
            .create(NewsApiService::class.java)
    }


    @ViewModelScoped
    @Provides
    fun provideCategoryApi(
            retrofitBuilder : Retrofit.Builder
    ) : CategoryApiService {
        return  retrofitBuilder
                .build()
                .create(CategoryApiService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun provideCategoryNetworkDataScope(categoryNetworkDataSourceImpl: CategoryNetworkDataSourceImpl) : CategoryNetworkDataSource = categoryNetworkDataSourceImpl

    @ViewModelScoped
    @Provides
    fun provideNewsNetworkDataScope(newsNetworkDataSourceImpl: NewsNetworkDataSourceImpl) : NewsNetworkDataSource = newsNetworkDataSourceImpl
}