package com.fdev.technogram.di

import com.fdev.technogram.datasource.network.NetworkConst
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
                .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(NetworkConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
    }
}
