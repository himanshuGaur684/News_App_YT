package com.news_details.details.model

import com.news_details.details_domain.model.SearchArticle


data class ArticleDTO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDTO?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)


fun ArticleDTO.toDomainArticle(): SearchArticle {
    return SearchArticle(
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt ?: "",
        title = title ?: "",
        urlToImage = urlToImage ?: ""
    )
}