package com.fdev.technogram.di

import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
import com.fdev.technogram.datasource.network.business.implementation.NewsNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class FragmentBind {



    @Binds
    abstract fun bindNewsNetworkDataSource(newsNetworkDataSourceImpl: NewsNetworkDataSourceImpl) : NewsNetworkDataSource



}