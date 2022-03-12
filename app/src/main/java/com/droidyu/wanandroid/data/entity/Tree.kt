package com.droidyu.wanandroid.data.entity

data class Tree(
    var isSelected: Boolean = false,
    val children: List<Tree>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)