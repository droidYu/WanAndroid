package com.droidyu.wanandroid.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.databinding.FragmentHomeBinding
import com.droidyu.wanandroid.ui.main.ArticleListFragment
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ArticleListFragment())
            .commit()
    }

    private fun observeData(binding: FragmentHomeBinding) {
        binding.apply {
            viewModel.bannerImgs.observe(viewLifecycleOwner) { result ->
                srl.isRefreshing = false
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
            banner.setAdapter(bannerAdapter)
                .addBannerLifecycleObserver(this@HomeFragment)
            banner.indicator = CircleIndicator(this@HomeFragment.context)
            srl.setOnRefreshListener {
                refreshData(binding)
            }
        }

    }

    private fun refreshData(binding: FragmentHomeBinding) {
        binding.apply {
            srl.isRefreshing = true
            viewModel.getBanner()
        }
    }

}