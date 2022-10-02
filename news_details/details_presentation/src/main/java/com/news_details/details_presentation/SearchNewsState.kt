package com.news_details.details_presentation


import com.news_details.details_domain.model.SearchArticle

data class SearchNewsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<SearchArticle>? = null
)