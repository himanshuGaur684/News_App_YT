package com.news_details.details_domain.use_case

import com.news_details.details_domain.model.SearchArticle
import com.news_details.details_domain.repository.SearchRepository
import com.universal.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<SearchArticle>>> =
        flow {
            emit(Resource.Loading())
            emit(searchRepository.getSearchResults(map))
        }.flowOn(Dispatchers.IO)

}