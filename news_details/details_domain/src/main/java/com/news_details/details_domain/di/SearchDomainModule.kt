package com.news_details.details_domain.di

import com.news_details.details_domain.repository.SearchRepository
import com.news_details.details_domain.use_case.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchDomainModule {

    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchNewsUseCase =
        SearchNewsUseCase(searchRepository)

}