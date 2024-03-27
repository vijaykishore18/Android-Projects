package com.example.newsapp.repository

import com.example.newsapp.dataBase.ArticleDataBase
import com.example.newsapp.models.Article
import com.example.newsapp.retrofit.RetrofitInstance

class NewsRepository (val db : ArticleDataBase) {

    suspend fun getHeadlines ( countryCode : String, pageNumber: Int) =
        RetrofitInstance.api.getHeadLines(countryCode, pageNumber)

    suspend fun getSearchForNews ( searchQuery : String, pageNumber: Int) =
        RetrofitInstance.api.getSearchForNews(searchQuery, pageNumber)

    suspend fun upsert (article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle (article: Article) = db.getArticleDao().deleteArticle(article)
}