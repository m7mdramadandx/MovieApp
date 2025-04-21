package com.telda.movieapp.features.home.di

import com.telda.movieapp.features.home.data.repo.HomeRepoImpl
import com.telda.movieapp.features.home.data.source.local.HomeLocalDataSource
import com.telda.movieapp.features.home.data.source.local.HomeLocalDataSourceImpl
import com.telda.movieapp.features.home.data.source.remote.HomeRemoteDataSource
import com.telda.movieapp.features.home.data.source.remote.HomeRemoteDataSourceImpl
import com.telda.movieapp.features.home.domain.repo.HomeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    abstract fun bindsLocalDataSource(localDataSource: HomeLocalDataSourceImpl): HomeLocalDataSource

    @Binds
    abstract fun bindsRepository(repository: HomeRepoImpl): HomeRepo

}