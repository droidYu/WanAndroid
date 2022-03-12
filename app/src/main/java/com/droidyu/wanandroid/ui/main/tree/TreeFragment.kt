package com.droidyu.wanandroid.ui.main.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.databinding.FragmentTreeBinding
import com.google.android.material.tabs.TabLayout
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
                        pager.offscreenPageLimit = 3
                        val titleList = mutableListOf<String>()
                        val fragmentList = mutableListOf<Fragment>()
                        for (tree in result.data) {
                            titleList.add(tree.name)
                            fragmentList.add(ChildTreeFragment(tree.children))
                        }
                        pager.adapter = TreeAdapter(childFragmentManager, titleList, fragmentList)
                    }
                    is WanResult.Error -> {

                    }
                }
            }
        }
    }


    class TreeAdapter(
        manager: FragmentManager,
        private val titleList: MutableList<String>,
        private val fragmentList: MutableList<Fragment>
    ) : FragmentPagerAdapter(manager) {
        override fun getCount(): Int {
            return titleList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }


    private fun initViews(fragmentTreeBinding: FragmentTreeBinding) {
        fragmentTreeBinding.run {
            tab.run {
                setupWithViewPager(pager)
                addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))
            }

            pager.run {
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))
            }
        }
    }
}