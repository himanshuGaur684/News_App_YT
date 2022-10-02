package com.news_articles.articles.repository

import com.universal.utils.Resource
import com.news_articles.articles.room.NewsDAO
import com.news_articles.news_domain.model.Article
import com.news_articles.news_domain.repository.NewsRepository
import com.news_articles.articles.model.toDomainArticle
import com.news_articles.articles.network.NewsApiService

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val newsDAO: NewsDAO
) : NewsRepository {

    override suspend fun getNewsArticle(): Resource<List<Article>> {
        return try {
            val data = newsApiService.getNewsArticles().articles.map { it.toDomainArticle() }
            Resource.Success(cacheArticleInRoom(data))
        } catch (e: Exception) {
            Resource.Error(e.message.toString(), data = getAllNewsArticleFromCache())
        }
    }

    override suspend fun cacheArticleInRoom(list: List<Article>): List<Article> {
        newsDAO.deleteAllArticle()
        list.forEach {
            newsDAO.insertArticle(it)
        }
        return getAllNewsArticleFromCache()
    }

    override suspend fun getAllNewsArticleFromCache(): List<Article> {
        return newsDAO.getAllNewsArticle()
    }
}