package com.news_articles.news_presentation

import com.news_articles.news_domain.model.Article


data class NewsState(
    val isLoading: Boolean = false,
    val data: List<Article>? = null,
    val error: String = ""
)
