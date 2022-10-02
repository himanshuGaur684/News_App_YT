package com.news_articles.articles.model

import com.news_articles.articles.model.ArticleDTO

data class NewsResponse(
    val articles: List<ArticleDTO>,
    val status: String,
    val totalResults: Int
)