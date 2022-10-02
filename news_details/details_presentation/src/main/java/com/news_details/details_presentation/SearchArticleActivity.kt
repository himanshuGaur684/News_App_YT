package com.news_details.details_presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.news_details.details_presentation.databinding.ActivitySearchArticleBinding
import com.universal.utils.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat

@AndroidEntryPoint
class SearchArticleActivity : AppCompatActivity() {

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, SearchArticleActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val searchViewModel: SearchViewModel by viewModels()

    private var _binding: ActivitySearchArticleBinding? = null
    private val binding: ActivitySearchArticleBinding
        get() = _binding!!

    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_search_article)

        initViews()
        initObservers()

    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            searchViewModel.searchNews.collectLatest {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.setData(it)
                }
            }
        }
    }

    private fun initViews() {
        binding.apply {
            rvArticle.adapter = newsAdapter

            ivTimer.setOnClickListener {

                val datePickerRange = MaterialDatePicker.Builder.dateRangePicker().build()
                datePickerRange.show(
                    this@SearchArticleActivity.supportFragmentManager,
                    "Date Picker"
                )
                datePickerRange.addOnPositiveButtonClickListener {
                    datePickerRange.selection?.let {
                        val map = mutableMapOf<String, String>()
                        Log.d("TAG", "initViews first: ${it.first}")
                        Log.d("TAG", "initViews second: ${it.second}")
                        map[Constants.START_DATE] = changeDateFormat(it.first)
                        map[Constants.END_DATE] = changeDateFormat(it.second)
                        map[Constants.QUERY] = binding.edSearch.text.toString()
                        map[Constants.API_KEY] = Constants.KEY
                        searchViewModel.getSearchNews(map)
                    }
                }
                datePickerRange.addOnCancelListener { }

            }
            edSearch.doOnTextChanged { text, _, _, _ ->
                val map = mutableMapOf<String, String>()
                map[Constants.QUERY] = text.toString()
                map[Constants.API_KEY] = Constants.KEY
                searchViewModel.getSearchNews(map)
            }
        }
    }

    fun changeDateFormat(long: Long?): String {
        long?.let {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            return simpleDateFormat.format(long)
        }
        return ""
    }

}

object GoToSearchActivity : Navigator {
    override fun navigate(activity: Activity) {
        SearchArticleActivity.launchActivity(activity)
    }
}
