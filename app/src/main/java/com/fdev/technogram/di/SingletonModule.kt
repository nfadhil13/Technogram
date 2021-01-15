package com.fdev.technogram.di

import android.content.Context
import android.content.SharedPreferences
import com.fdev.technogram.datasource.network.NetworkConst
import com.fdev.technogram.datasource.network.business.abstraction.CategoryNetworkDataSource
import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
import com.fdev.technogram.datasource.network.business.implementation.CategoryNetworkDataSourceImpl
import com.fdev.technogram.datasource.network.business.implementation.NewsNetworkDataSourceImpl
import com.fdev.technogram.util.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {


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

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context
            .getSharedPreferences(
                AppConstants.APP_PREFENCES,
                Context.MODE_PRIVATE
            )
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesEditor(
        sharedPreferences: SharedPreferences
    ): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }



}
