package com.droidyu.wanandroid.ui.main.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.databinding.FragmentTreeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TreeFragment : Fragment() {

    private val viewModel: TreeViewModel by lazy { ViewModelProvider(this)[TreeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentTreeBinding>(
            inflater,
            R.layout.fragment_tree,
            container,
            false
        ).apply {
            initViews(this)
            observeData(this)
            viewModel.getTree()

        }.root
    }

    private fun observeData(fragmentTreeBinding: FragmentTreeBinding) {
        fragmentTreeBinding.apply {
            viewModel.trees.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is WanResult.Success -> {
                        val titleList = mutableListOf<String>()
                        val fragmentList = mutableListOf<Fragment>()
                        for (tree in result.data) {
                            titleList.add(tree.name)
                            fragmentList.add(ChildTreeFragment(tree.children))
                        }
                        pager.adapter = TreeAdapter(childFragmentManager, lifecycle, fragmentList)

                        TabLayoutMediator(
                            tab, pager
                        ) { tab, position ->
                            tab.text = titleList[position]
                        }.attach()

                    }
                    is WanResult.Error -> {

                    }
                }
            }
        }
    }


    class TreeAdapter(
        manager: FragmentManager,
        lifecycle: Lifecycle,
        private val fragmentList: MutableList<Fragment>
    ) : FragmentStateAdapter(manager, lifecycle) {
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }


    private fun initViews(fragmentTreeBinding: FragmentTreeBinding) {
        fragmentTreeBinding.run {
            tab.run {

            }

            pager.run {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                })
            }
        }
    }
}