package com.droidyu.wanandroid.ui.main.tree

import androidx.lifecycle.ViewModel
import com.droidyu.wanandroid.data.entity.Tree
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChildTreeViewModel @Inject constructor() : ViewModel() {

    val childTreeList = mutableListOf<Tree>()

    fun setData(childTree: List<Tree>) {
        childTreeList.addAll(childTree)
    }
}