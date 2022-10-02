package com.news_details.details.di


import com.news_details.details.network.SearchApiService
import com.news_details.details.repository.SearchRepositoryImpl
import com.news_details.details_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {

    @Singleton
    @Provides
    fun provideSearchApiService(retrofit: Retrofit): SearchApiService =
        retrofit.create(SearchApiService::class.java)

    @Singleton
    @Provides
    fun provideSearchRepo(searchApiService: SearchApiService): SearchRepository =
        SearchRepositoryImpl(searchApiService)


}