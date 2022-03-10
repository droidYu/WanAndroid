package com.droidyu.wanandroid.ui.home

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
import com.droidyu.wanandroid.databinding.FragmentHomeBinding
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    private val adapter: HomeArticleAdapter by lazy { HomeArticleAdapter(mutableListOf()) }

    private val bannerAdapter: BannerImgAdapter by lazy { BannerImgAdapter(mutableListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            initViews(this)
            observeData(this)
            refreshData(this)
        }.root
    }

    private fun observeData(binding: FragmentHomeBinding) {
        binding.apply {
            viewModel.articleList.observe(viewLifecycleOwner) { result ->
                srl.isRefreshing = false
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
            viewModel.bannerImgs.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is WanResult.Success -> {
                        bannerAdapter.setDatas(result.data)
                    }
                    is WanResult.Error -> {

                    }
                }
            }
        }
    }

    private fun initViews(binding: FragmentHomeBinding) {
        binding.apply {
            vm = viewModel
            rv.layoutManager = LinearLayoutManager(context)
            srl.setOnRefreshListener {
                refreshData(this)
            }
            rv.adapter = adapter
            rv.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

            banner.setAdapter(bannerAdapter)
                .addBannerLifecycleObserver(this@HomeFragment)
            banner.indicator = CircleIndicator(this@HomeFragment.context)
        }
    }

    private fun refreshData(binding: FragmentHomeBinding) {
        binding.apply {
            srl.isRefreshing = true
            viewModel.refresh()
            viewModel.getBanner()
        }
    }

}