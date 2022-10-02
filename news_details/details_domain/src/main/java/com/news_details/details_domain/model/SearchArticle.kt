package com.news_details.details_domain.model

data class SearchArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String
)