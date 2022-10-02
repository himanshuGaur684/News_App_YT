package com.news_details.details.network

import com.news_details.details.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchApiService {

    @GET("everything")
    suspend fun getSearchResult(
        @QueryMap map: MutableMap<String, String>
    ): SearchResponse

}