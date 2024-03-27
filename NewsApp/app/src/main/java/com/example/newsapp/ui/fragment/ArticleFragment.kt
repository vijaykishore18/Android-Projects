package com.example.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.models.Article
import com.example.newsapp.models.Source
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar



class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as MainActivity).newsViewModel
        val id = arguments?.getString("id") ?: ""
        val author = arguments?.getString("author")?: ""
        val content = arguments?.getString("content")?: ""
        val description = arguments?.getString("description")?: ""
        val publishedAt = arguments?.getString("publishedAt")?: ""
        val sourceID = arguments?.getString("sourceID")?: ""
        val sourceName = arguments?.getString("sourceName")?: ""
        val title = arguments?.getString("title")?: ""
        val url = arguments?.getString("url")?: ""
        val urlToImage = arguments?.getString("urlToImage")?: ""

        val article = Article(
            id.takeIf { it.isNotEmpty() } ?.toInt() ?: 0,
            author,
            content,
            description,
            publishedAt,
            Source(
                 sourceID,
                sourceName
            ),
            title,
            url,
            urlToImage
        )


        binding.webView.apply{
            webViewClient = WebViewClient()
            loadUrl(url)
        }

        binding.saveButton.setOnClickListener{
            newsViewModel.addToSave(article)
            Snackbar.make(view,"Added to Saved!",Snackbar.LENGTH_SHORT).show()
        }
    }
}