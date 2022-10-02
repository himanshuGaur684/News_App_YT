package com.news_details.details.model

import com.news_details.details.model.ArticleDTO


data class SearchResponse(
    val articles: List<ArticleDTO>,
    val status: String,
    val totalResults: Int
)