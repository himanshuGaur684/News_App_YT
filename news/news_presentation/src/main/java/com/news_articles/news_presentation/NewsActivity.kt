package com.news_articles.news_presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.app.utils.navigation.Activities
import com.news.presentation.NewsAdapter
import com.news_articles.news_presentation.databinding.ActivityNewsBinding
import com.universal.utils.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding
        get() = _binding!!
    private val newsAdapter = NewsAdapter()

    @Inject
    lateinit var navigatorProvider: Navigator.Provider

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, NewsActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        setContentView(binding.root)

        initView()
        viewModelObservables()

    }

    private fun initView() {
        binding.apply {
            ivGoToSearch.setOnClickListener {
                navigatorProvider.getActivity(Activities.SearchActivity).navigate(this@NewsActivity)
            }
        }
    }

    private fun viewModelObservables() {
        lifecycleScope.launchWhenStarted {
            newsViewModel.newsArticle.collectLatest {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@NewsActivity, it.error, Toast.LENGTH_LONG).show()
                    it.data?.let {
                        if (it.isNotEmpty()) {
                            newsAdapter.setData(it)
                        }
                    }
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    binding.rvNewsArticle.adapter = newsAdapter
                    newsAdapter.setData(it)
                }
            }
        }

    }
}

object GoToNewsActivity : Navigator {
    override fun navigate(activity: Activity) {
        NewsActivity.launchActivity(activity)
    }
}