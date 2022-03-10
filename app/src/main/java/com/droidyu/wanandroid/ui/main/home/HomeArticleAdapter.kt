package com.droidyu.wanandroid.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.Article
import com.droidyu.wanandroid.databinding.ItemArticleBinding
import javax.inject.Inject

class HomeArticleAdapter @Inject constructor(var data: List<Article>) :
    RecyclerView.Adapter<HomeArticleAdapter.BindingHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        return BindingHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BindingHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(article: Article) {
            binding.article = article
        }
    }
}

