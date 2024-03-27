package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.Article
import okhttp3.internal.http2.Http2Connection

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){
    inner class ArticleViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)


    private val differCallBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onItemClickListner : ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.newsImg.setImageResource(R.drawable.news_image)
        holder.binding.newsTitle.text = article.title
        holder.binding.newsDesc.text = article.description
        holder.binding.newsSource.text = article.source.name
        holder.binding.newsDate.text = article.publishedAt

        holder.binding.apply {
            Glide.with(holder.itemView.context).load(article.urlToImage)
                .into(holder.binding.newsImg)
            newsTitle.text = article.source?.name
            newsSource.text = article.title
            newsDesc.text = article.description
            newsDate.text = article.publishedAt
        }

        holder.binding.root.setOnClickListener{
            onItemClickListner?.let {
                it(article)
            }
        }
    }
    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListner = listener
    }
}