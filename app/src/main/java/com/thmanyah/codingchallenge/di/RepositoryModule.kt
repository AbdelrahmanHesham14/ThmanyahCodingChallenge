package com.thmanyah.codingchallenge.di

import com.thmanyah.data.repository.RemoteDataSource
import com.thmanyah.data.repository.SectionRepositoryImp
import com.thmanyah.domain.repository.SectionRepository
import com.thmanyah.remote.source.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun provideSectionRepository(repository : SectionRepositoryImp) : SectionRepository

}