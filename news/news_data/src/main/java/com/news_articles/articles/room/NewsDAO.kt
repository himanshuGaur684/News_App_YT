package com.news_articles.articles.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.news_articles.news_domain.model.Article

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM article")
    suspend fun getAllNewsArticle(): List<Article>

    @Query("DELETE FROM article")
    suspend fun deleteAllArticle()

}