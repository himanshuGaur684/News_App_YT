package com.news_articles.news_domain.repository

import com.news_articles.news_domain.model.Article
import com.universal.utils.Resource

interface NewsRepository {

    suspend fun getNewsArticle(): Resource<List<Article>>

    suspend fun cacheArticleInRoom(list: List<Article>): List<Article>

    suspend fun getAllNewsArticleFromCache(): List<Article>

}