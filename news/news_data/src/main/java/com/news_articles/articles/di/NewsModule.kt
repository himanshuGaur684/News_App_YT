package com.news_articles.articles.di

import android.content.Context
import com.news_articles.articles.network.NewsApiService
import com.news_articles.articles.repository.NewsRepositoryImpl
import com.news_articles.articles.room.NewsDAO
import com.news_articles.articles.room.NewsDatabase
import com.news_articles.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NewsModule {

    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService, newsDAO: NewsDAO): NewsRepository {
        return NewsRepositoryImpl(newsApiService, newsDAO)
    }

    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
        NewsDatabase.getInstance(context)

    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDAO = newsDatabase.getNewsDao()


}