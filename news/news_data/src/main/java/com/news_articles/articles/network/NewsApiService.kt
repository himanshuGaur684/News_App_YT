package com.news_articles.articles.network

import com.news_articles.articles.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String = "in",
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = "36091782782f4cd4831d584627cabe6e"
    ): NewsResponse

}