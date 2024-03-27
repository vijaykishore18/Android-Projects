package com.example.newsapp.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class SavedFragment : Fragment(R.layout.fragment_saved) {
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentSavedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)

        newsViewModel = (activity as MainActivity).newsViewModel
        setUpSavedRecycler()

        newsAdapter.setOnItemClickListener {
            val bundle = bundleOf(
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
            findNavController().navigate(R.id.action_savedFragment2_to_articleFragment, bundle)
        }

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                newsViewModel.deleteFromSaved(article)
                Snackbar.make(view,"Removed from Saved",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        newsViewModel.addToSave(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.savedRv)
        }
        newsViewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })

    }

    private fun setUpSavedRecycler() {
        newsAdapter = NewsAdapter()
        binding.savedRv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}