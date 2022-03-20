package com.droidyu.wanandroid.ui.main.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.Tree
import com.droidyu.wanandroid.databinding.FragmentChildTreeBinding
import com.droidyu.wanandroid.ui.main.article.list.ArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChildTreeFragment(private var childTree: List<Tree>) : Fragment() {

    val viewModel: ChildTreeViewModel by lazy { ViewModelProvider(this)[ChildTreeViewModel::class.java] }

    constructor() : this(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setData(childTree)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentChildTreeBinding>(
            inflater,
            R.layout.fragment_child_tree,
            container,
            false
        ).apply {
            childTree = viewModel.childTreeList
            val sb = StringBuffer()
            for (tree in childTree) {
                sb.append("${tree.name},${tree.id}")
                sb.append("\n")

            }
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            val adapter = ChildTreeAdapter(childTree)
            adapter.setItemClickListener(object : ChildTreeAdapter.OnClickListener {
                override fun onItemClick(tree: Tree) {
                    for (selectTree in childTree) {
                        selectTree.isSelected = selectTree.id == tree.id
                    }
                    adapter.notifyDataSetChanged()
                    childFragmentManager.beginTransaction().replace(
                        R.id.child_container,
                        ArticleListFragment(tree.id.toString())
                    ).commit()

                }
            })
            rv.adapter = adapter

            if (childTree.isNotEmpty()) {
                var noSelected = true
                var selectedId = ""
                for (tree in childTree) {
                    if (tree.isSelected) {
                        noSelected = false
                        selectedId = tree.id.toString()
                    }
                }
                if (noSelected) {
                    childTree[0].isSelected = true
                    selectedId = childTree[0].id.toString()
                }

                childFragmentManager.beginTransaction().replace(
                    R.id.child_container,
                    ArticleListFragment(selectedId)
                ).commit()
            }
        }.root

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        var id = ""
//        if (childTree.isNotEmpty()) {
//            id=childTree[0].id.toString()
//        }
//        parentFragmentManager.beginTransaction().replace(R.id.child_container, ArticleListFragment(id))
//            .commit()
//    }
}