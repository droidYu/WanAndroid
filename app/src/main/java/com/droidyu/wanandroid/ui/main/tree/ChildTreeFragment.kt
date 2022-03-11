package com.droidyu.wanandroid.ui.main.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.Tree
import com.droidyu.wanandroid.databinding.FragmentChildTreeBinding

class ChildTreeFragment(private val childTree: List<Tree>) : Fragment() {

    constructor() : this(mutableListOf())

    val titleList = mutableListOf<String>()

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
            val sb = StringBuffer()
            for (tree in childTree) {
                sb.append("${tree.name},${tree.id}")
                sb.append("\n")

            }
            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rv.adapter = ChildTreeAdapter(childTree)
//            tv.visibility=View.GONE
//            parentFragmentManager.beginTransaction().replace(R.id.child_container,ArticleListFragment(childTree[0].id.toString())).commit()
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