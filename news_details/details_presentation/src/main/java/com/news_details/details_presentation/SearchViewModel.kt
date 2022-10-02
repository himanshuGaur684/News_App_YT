package com.news_details.details_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news_details.details_domain.use_case.SearchNewsUseCase
import com.universal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchNewsUseCase: SearchNewsUseCase) :
    ViewModel() {


    private val _searchNews: MutableStateFlow<SearchNewsState> = MutableStateFlow(SearchNewsState())
    val searchNews: StateFlow<SearchNewsState> = _searchNews


    fun getSearchNews(map: MutableMap<String, String>) {
        searchNewsUseCase(map).onEach {
            when (it) {
                is Resource.Loading -> {
                    _searchNews.value = SearchNewsState(isLoading = true)
                }
                is Resource.Success -> {
                    _searchNews.value = SearchNewsState(data = it.data)
                }
                is Resource.Error -> {
                    _searchNews.value = SearchNewsState(error = it.message)
                }
                is Resource.Idle -> {
                    _searchNews.value = SearchNewsState()
                }
            }


        }.launchIn(viewModelScope)
    }


}