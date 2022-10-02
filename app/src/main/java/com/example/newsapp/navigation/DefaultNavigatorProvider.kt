package com.example.newsapp.navigation

import com.app.utils.navigation.Activities
import com.news_articles.news_presentation.GoToNewsActivity
import com.news_details.details_presentation.GoToSearchActivity
import com.universal.utils.navigation.Navigator

class DefaultNavigatorProvider : Navigator.Provider {
    override fun getActivity(activities: Activities): Navigator = when (activities) {
        is Activities.NewsActivity -> GoToNewsActivity
        is Activities.SearchActivity -> GoToSearchActivity
    }
}
