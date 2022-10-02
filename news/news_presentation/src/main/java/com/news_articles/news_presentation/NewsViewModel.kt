package com.news_articles.news_presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.utils.navigation.Activities
import com.news_articles.news_domain.use_cases.GetNewsArticlesUseCase
import com.universal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase
) : ViewModel() {

    private val _newsArticle = MutableStateFlow<NewsState>(NewsState())
    val newsArticle: StateFlow<NewsState> = _newsArticle

    val navigateTo = MutableLiveData<Activities>()


    init {
        navigateTo.value = Activities.SearchActivity
        getNewsArticle()
    }

    fun getNewsArticle() {
        getNewsArticlesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _newsArticle.value = NewsState(isLoading = true)
                }
                is Resource.Error -> {
                    _newsArticle.value = NewsState(error = it.message, data = it.data)
                }
                is Resource.Success -> {
                    _newsArticle.value = NewsState(data = it.data)
                }
                is Resource.Idle -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}