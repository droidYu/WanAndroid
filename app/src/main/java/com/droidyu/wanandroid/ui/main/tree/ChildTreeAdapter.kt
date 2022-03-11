package com.droidyu.wanandroid.ui.main.tree

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.Tree
import com.droidyu.wanandroid.databinding.ItemChildTreeBinding

class ChildTreeAdapter(private val data: List<Tree>) :
    RecyclerView.Adapter<ChildTreeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemChildTreeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_child_tree, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tree = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(val binding: ItemChildTreeBinding) : RecyclerView.ViewHolder(binding.root)
}