package com.example.newsapp.ui.fragment


import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentExploreBinding
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var retryButton: Button
    lateinit var errorText: TextView
    lateinit var itemSearchError: View
    lateinit var binding: FragmentExploreBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentExploreBinding.bind(view)

        itemSearchError = binding.exploreNetworkError.root
        retryButton = itemSearchError.findViewById(R.id.retry_button)
        errorText = itemSearchError.findViewById(R.id.error_text)

        newsViewModel = (activity as MainActivity).newsViewModel
        setUpExploreRecycler()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_exploreFragment2_to_articleFragment, bundle)
        }

        var job: Job? = null
       // var job: Job

        binding.searchEdit.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(Constants.SEARCH_NEWS_DELAY_TIME)
                // Perform search operation here
                if (editable.toString().isNotEmpty()) {
                    newsViewModel.searchNews(editable.toString())
                } else {
                    hideErrorMessage()
                }
            }
        }

        newsViewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Error<*> -> {
                    hideErrorMessage()
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / Constants.QUERY_PAGE_SIZE + 2
                        isLastPage = newsViewModel.searchNewsPage == totalPages

                        if (isLastPage) {
                            binding.exploreRv.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Loading<*> -> {
                    hideProgressBar()
                    response.data?.let { message ->
                        Toast.makeText(activity, "Sorry Error : $message", Toast.LENGTH_LONG).show()
                        showErrorMessage("$message")
                    }
                }
                is Resource.Success<*> -> {
                    showProgressBar()
                }
            }
        })

        retryButton.setOnClickListener {
            if (binding.searchEdit.text.toString().isNotEmpty()) {
                newsViewModel.searchNews(binding.searchEdit.text.toString())
            } else {
                hideErrorMessage()
            }
        }
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    fun hideProgressBar() {
        binding.exploreProgressbar.visibility = View.INVISIBLE
        isLoading = false
    }

    fun showProgressBar() {
        binding.exploreProgressbar.visibility = View.VISIBLE
        isLoading = true
    }

    fun hideErrorMessage() {
        itemSearchError.visibility = View.INVISIBLE
        isError = false
    }

    fun showErrorMessage(message: String) {
        itemSearchError.visibility = View.VISIBLE
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
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition > 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPageInate =
                isNoError && isNotLoadingAndIsNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPageInate) {
                newsViewModel.searchNews(binding.searchEdit.text.toString())
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

    fun setUpExploreRecycler() {
        newsAdapter = NewsAdapter()
        binding.exploreRv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@ExploreFragment.scrollListener)
        }
    }
}
