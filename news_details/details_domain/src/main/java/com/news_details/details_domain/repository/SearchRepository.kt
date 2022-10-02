package com.news_details.details_domain.repository


import com.news_details.details_domain.model.SearchArticle
import com.universal.utils.Resource

interface SearchRepository {
    suspend fun getSearchResults(map: MutableMap<String, String>): Resource<List<SearchArticle>>
}