package com.news_articles.articles.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.news_articles.news_domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): NewsDatabase {
            return Room.databaseBuilder(context, NewsDatabase::class.java, "news_db")
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getNewsDao(): NewsDAO
}