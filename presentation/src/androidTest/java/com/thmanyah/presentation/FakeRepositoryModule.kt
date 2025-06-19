package com.thmanyah.presentation

import com.thmanyah.domain.repository.SectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@InstallIn(SingletonComponent::class)
object FakeRepositoryModule {

    @Provides
    fun provideFakeSectionRepository(): SectionRepository {
        return FakeSectionRepository()
    }
}