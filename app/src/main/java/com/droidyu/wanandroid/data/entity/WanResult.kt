package com.droidyu.wanandroid.data.entity

sealed class WanResult<out R> {
    data class Success<out T>(val data: T) : WanResult<T>()
    data class Error(val exception: Exception) : WanResult<Nothing>()
}