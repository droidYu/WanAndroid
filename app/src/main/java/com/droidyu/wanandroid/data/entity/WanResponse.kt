package com.droidyu.wanandroid.data.entity

data class WanResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)