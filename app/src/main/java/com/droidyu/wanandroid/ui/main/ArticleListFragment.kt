package com.droidyu.wanandroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.databinding.FragmentArticleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment(private var cId: String) : Fragment() {
    private val adapter: ArticleListAdapter by lazy { ArticleListAdapter(mutableListOf()) }

    private val viewModel: ArticleListViewModel by lazy { ViewModelProvider(this)[ArticleListViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.cId = cId
    }

    constructor() : this("")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentArticleListBinding>(
            inflater,
            R.layout.fragment_article_list,
            container,
            false
        ).apply {
            cId = viewModel.cId
            initViews(this)
            observeData(this)
            viewModel.refresh(cId)
        }.root
    }

    private fun observeData(binding: FragmentArticleListBinding) {
        binding.apply {
            viewModel.articleList.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is WanResult.Success -> {
                        adapter.data = result.data
                        for ((index, article) in adapter.data.withIndex()) {
                            adapter.notifyItemChanged(index, article)
                        }
                    }
                    is WanResult.Error -> {

                    }
                }
            }
        }
    }

    private fun initViews(binding: FragmentArticleListBinding) {
        binding.apply {
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = adapter
            rv.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        }
    }

}