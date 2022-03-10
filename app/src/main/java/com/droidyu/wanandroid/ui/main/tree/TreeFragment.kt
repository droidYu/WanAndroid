package com.droidyu.wanandroid.ui.main.tree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.databinding.FragmentTreeBinding

class TreeFragment : Fragment() {
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

        }.root
    }
}