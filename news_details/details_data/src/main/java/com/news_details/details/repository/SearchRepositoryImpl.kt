package com.news_details.details.repository

import android.util.Log
import com.news_details.details.model.toDomainArticle
import com.news_details.details.network.SearchApiService
import com.news_details.details_domain.model.SearchArticle
import com.news_details.details_domain.repository.SearchRepository
import com.universal.utils.Resource

class SearchRepositoryImpl(private val searchApiService: SearchApiService) : SearchRepository {
    override suspend fun getSearchResults(map: MutableMap<String, String>): Resource<List<SearchArticle>> {
        return try {
            map.forEach {
                Log.d("TAG", "getSearchResults: key ${it.key} value: ${it.value} ")
            }
            val result = searchApiService.getSearchResult(map).articles.map { it.toDomainArticle() }
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}