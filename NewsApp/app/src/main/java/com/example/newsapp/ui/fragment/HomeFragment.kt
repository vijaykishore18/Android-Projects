package com.example.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Resource

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding : FragmentHomeBinding
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var retryButton: Button
    lateinit var errorText : TextView
    lateinit var itemHeadlinesError : View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        itemHeadlinesError = view.findViewById(R.id.home_network_error)
//        val view : View = inflater.inflate(R.layout.network_error,null)

        retryButton = itemHeadlinesError.findViewById(R.id.retry_button)
        errorText = itemHeadlinesError.findViewById(R.id.error_text)

        newsViewModel = (activity as MainActivity).newsViewModel
        setUpHeadlinesRecycler()

        newsAdapter.setOnItemClickListener {
            val bundle1 = bundleOf(
                "id" to it.id,
                "author" to it.author,
                "content" to it.content,
                "description" to it.description,
                "publishedAt" to it.publishedAt,
                "sourceID" to it.source.id,
                "sourceName" to it.source.name,
                "title" to it.title,
                "url" to it.url,
                "urlToImage" to it.urlToImage,
            )

            findNavController().navigate(R.id.action_homeFragment_to_articleFragment, bundle1)
        }

        newsViewModel.headlines.observe(viewLifecycleOwner, Observer { response ->
            when(response){

                is Resource.Error <*> -> {
                    hideErrorMessage()
                    hideProgressBar()
                    Log.i("NewsAdapter","news" )
                    response.data?.let {newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / Constants.QUERY_PAGE_SIZE + 2
                        isLastPage = newsViewModel.headlinesPage == totalPages

                        if (isLastPage){
                            binding.homeRv.setPadding(0,0,0,0)
                        }
                    }
                }
                is Resource.Loading <*> -> {
                    hideProgressBar()
                    response.data?.let { message ->
                        Toast.makeText(activity,"Sorry Error : $message",Toast.LENGTH_LONG).show()
                        showErrorMessage("$message")
                    }
                }
                is Resource.Success <*> -> {
                    response.data?.let {newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / Constants.QUERY_PAGE_SIZE + 2
                        isLastPage = newsViewModel.headlinesPage == totalPages

                        if (isLastPage){
                            binding.homeRv.setPadding(0,0,0,0)
                        }
                    }
                }
            }
        })

        retryButton.setOnClickListener{
            hideErrorMessage()
            showProgressBar()
            newsViewModel.getHeadlines("us")
        }
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    fun hideProgressBar(){
        binding.homeProgressbar.visibility = View.INVISIBLE
        isLoading = false
    }

    fun showProgressBar(){
        binding.homeProgressbar.visibility = View.VISIBLE
        isLoading = true
    }

    fun hideErrorMessage(){
        itemHeadlinesError.visibility = View.INVISIBLE
        isError = false
    }

    fun showErrorMessage(message: String){
        itemHeadlinesError.visibility = View.VISIBLE
        errorText.text = message
        isError = true
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoError = !isError
            val isNotLoadingAndIsNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= 0
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPageInate =
                isNoError && isNotLoadingAndIsNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPageInate) {
                newsViewModel.getHeadlines("us")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }
        fun setUpHeadlinesRecycler(){
            newsAdapter = NewsAdapter()
            binding.homeRv.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(activity)
                addOnScrollListener(this@HomeFragment.scrollListener)
            }
        }
}