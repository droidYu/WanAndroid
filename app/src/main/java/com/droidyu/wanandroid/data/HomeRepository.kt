package com.droidyu.wanandroid.data

import com.droidyu.wanandroid.data.entity.ArticlePage
import com.droidyu.wanandroid.data.entity.WanResponse
import com.droidyu.wanandroid.network.Api
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: Api,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun refresh(): WanResponse<ArticlePage> {
        return withContext(defaultDispatcher) {
            api.getArticle(0)
        }
    }

    suspend fun loadMore(page: Int): WanResponse<ArticlePage> {
        return withContext(defaultDispatcher) {
            api.getArticle(page)
        }
    }
}