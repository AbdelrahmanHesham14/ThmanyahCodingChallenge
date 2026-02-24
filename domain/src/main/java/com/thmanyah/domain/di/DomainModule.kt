package com.thmanyah.domain.di

import com.thmanyah.domain.repository.SectionRepository
import com.thmanyah.domain.usecase.GetHomeSectionsUseCase
import com.thmanyah.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideSearchUseCase(sectionRepository: SectionRepository): SearchUseCase {
        return SearchUseCase(sectionRepository)
    }

    @ViewModelScoped
    @Provides
    fun provideGetHomeSectionsUseCase(sectionRepository: SectionRepository): GetHomeSectionsUseCase {
        return GetHomeSectionsUseCase(sectionRepository)
    }

}