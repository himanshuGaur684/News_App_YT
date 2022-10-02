package com.news_articles.news_domain.use_cases

import com.news_articles.news_domain.model.Article
import com.news_articles.news_domain.repository.NewsRepository
import com.universal.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetNewsArticlesUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        emit(newsRepository.getNewsArticle())
    }.flowOn(Dispatchers.IO)

}