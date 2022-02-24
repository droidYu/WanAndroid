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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    private val adapter = HomeArticleAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentHomeBinding?>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            vm = viewModel
            rv.layoutManager = LinearLayoutManager(context)
            srl.setOnRefreshListener {
                refreshData(srl)
            }
            rv.adapter = adapter
            rv.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            viewModel.articleList.observe(viewLifecycleOwner) { result ->
                srl.isRefreshing = false
                when (result) {
                    is WanResult.Success -> {
                        adapter.data = result.data
                        adapter.notifyDataSetChanged()
                    }
                    is WanResult.Error -> {

                    }
                }
            }
            refreshData(srl)
        }.root
    }

    private fun refreshData(srl: SwipeRefreshLayout) {
        srl.isRefreshing = true
        viewModel.refresh()
    }

}