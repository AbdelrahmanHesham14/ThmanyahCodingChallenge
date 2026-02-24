package com.thmanyah.data.di

import com.thmanyah.data.repository.SectionRepositoryImp
import com.thmanyah.domain.repository.SectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SectionRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideSectionRepository(repository : SectionRepositoryImp) : SectionRepository

}