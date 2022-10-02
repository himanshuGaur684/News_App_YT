package com.news_articles.news_domain.di

import com.news_articles.news_domain.repository.NewsRepository
import com.news_articles.news_domain.use_cases.GetNewsArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsHiltModulesDomain {

    @Provides
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsArticlesUseCase {
        return GetNewsArticlesUseCase(newsRepository)
    }

}