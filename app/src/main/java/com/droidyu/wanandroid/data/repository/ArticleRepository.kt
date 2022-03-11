package com.droidyu.wanandroid.data.repository

import com.droidyu.wanandroid.data.entity.ArticlePage
import com.droidyu.wanandroid.data.entity.BannerImg
import com.droidyu.wanandroid.data.entity.Tree
import com.droidyu.wanandroid.data.entity.WanResponse
import com.droidyu.wanandroid.network.Api
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val api: Api,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun refresh(cId: String): WanResponse<ArticlePage> {
        return withContext(defaultDispatcher) {
            if (cId.isEmpty()) {
                api.getArticle(0)
            } else {
                api.getArticleByCId(0, cId)
            }
        }
    }

    suspend fun loadMore(page: Int, cId: String): WanResponse<ArticlePage> {
        return withContext(defaultDispatcher) {
            if (cId.isEmpty()) {
                api.getArticle(page)
            } else {
                api.getArticleByCId(page, cId)
            }
        }
    }

    suspend fun getBanner(): WanResponse<List<BannerImg>> {
        return withContext(defaultDispatcher) {
            api.getBanner()
        }
    }

    suspend fun getTree(): WanResponse<List<Tree>> {
        return withContext(defaultDispatcher) {
            api.getTree()
        }

    }
}