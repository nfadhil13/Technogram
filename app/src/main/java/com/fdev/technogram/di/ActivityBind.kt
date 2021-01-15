//package com.fdev.technogram.di
////
////import com.fdev.technogram.datasource.network.business.abstraction.CategoryNetworkDataSource
////import com.fdev.technogram.datasource.network.business.abstraction.NewsNetworkDataSource
////import com.fdev.technogram.datasource.network.business.implementation.CategoryNetworkDataSourceImpl
////import com.fdev.technogram.datasource.network.business.implementation.NewsNetworkDataSourceImpl
////import dagger.Binds
////import dagger.Module
////import dagger.hilt.InstallIn
////import dagger.hilt.android.components.ActivityComponent
////import dagger.hilt.android.components.FragmentComponent
////import dagger.hilt.android.scopes.ActivityScoped
////
////@Module
////@InstallIn(ActivityComponent::class)
////abstract class ActivityBind {
////
////
////    @ActivityScoped
////    @Binds
////    abstract fun bindNewsNetworkDataSource(newsNetworkDataSourceImpl: NewsNetworkDataSourceImpl) : NewsNetworkDataSource
////
////    @ActivityScoped
////    @Binds
////    abstract fun bindCategoryNetworkDataSource(categoryNetworkDataSourceImpl: CategoryNetworkDataSourceImpl) : CategoryNetworkDataSource
////
////
////}