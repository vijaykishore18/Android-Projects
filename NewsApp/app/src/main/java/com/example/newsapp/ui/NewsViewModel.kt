package com.example.newsapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(app : Application, val newsRepository: NewsRepository) : AndroidViewModel(app) {
    val headlines : MutableLiveData<Resource<NewsResponse>> =  MutableLiveData()
    var headlinesPage = 1
    var headlinesResponse : NewsResponse ?= null

    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse : NewsResponse ?= null
    var newSearchQuery : String ?= null
    var oldSearchQuery : String ?= null

    init {
        getHeadlines("us")
    }

    fun getHeadlines(countryCode: String) = viewModelScope.launch {
        headlinesInternet(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchQueryInternet(searchQuery)
    }

    fun handleHeadlinesResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful) let {
            response.body()?.let { resultResponse ->
                headlinesPage++
                if (headlinesResponse == null){
                    headlinesResponse = resultResponse
                }
                else{
                    val oldArticle = headlinesResponse ?.articles
                    val newArticle = resultResponse.articles
                    oldArticle?.addAll(newArticle)
                }
                return Resource.Success(headlinesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handelSearchNewsResponse(response : Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) let {
            response.body()?.let {resultResponse ->
                if (searchNewsResponse == null || newSearchQuery != oldSearchQuery ){
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                }
                else{
                    searchNewsPage++
                    val oldArticles = searchNewsResponse ?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun addToSave(article: Article) = viewModelScope.launch{
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteFromSaved(article: Article) = viewModelScope.launch{
        newsRepository.deleteArticle(article)
    }

    fun internetConnection(context: Context) : Boolean{
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when{
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }

    suspend fun headlinesInternet(countryCode: String){
        headlines.postValue(Resource.Loading())

        try {
            if (internetConnection(this.getApplication())){
                val response = newsRepository.getHeadlines(countryCode, headlinesPage)
                headlines.postValue(handleHeadlinesResponse(response))
            }
            else{
                headlines.postValue(Resource.Error("NO INTERNET!"))
            }
        }
        catch (t : Throwable){
            when(t){
            is IOException -> headlines.postValue(Resource.Error("UNABLE TO CONNECT!"))
            else -> headlines.postValue(Resource.Error("NO SIGNAL!"))
            }
        }
    }
    private suspend fun searchQueryInternet(searchQuery: String){
        newSearchQuery = searchQuery
        searchNews.postValue(Resource.Loading())

        try {
            if (internetConnection(this.getApplication())){
                val response = newsRepository.getSearchForNews(searchQuery, headlinesPage)
                searchNews.postValue(handelSearchNewsResponse(response))
            }
            else{
                searchNews.postValue(Resource.Error("NO INTERNET!"))
            }
        }
        catch (t : Throwable){
            when(t){
                is IOException -> searchNews.postValue(Resource.Error("UNABLE TO CONNECT!"))
                else -> searchNews.postValue(Resource.Error("NO SIGNAL!"))
                }
            }
        }
}
