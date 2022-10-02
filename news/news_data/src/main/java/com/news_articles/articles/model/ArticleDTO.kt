package com.news_articles.articles.model

import com.news_articles.news_domain.model.Article

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


fun ArticleDTO.toDomainArticle(): Article {
    return Article(
        author=author?:"",
        content=content?:"",
        description=description?:"",
        publishedAt=publishedAt?:"",
        title=title?:"",
        urlToImage=urlToImage?:""
    )
}