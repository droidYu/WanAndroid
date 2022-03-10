package com.droidyu.wanandroid.network

import com.droidyu.wanandroid.data.entity.ArticlePage
import com.droidyu.wanandroid.data.entity.BannerImg
import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @POST("user/login")
    suspend fun login(
        @Query("username") userName: String,
        @Query("password") passWord: String
    ): WanResponse<User>

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): WanResponse<ArticlePage>

    @GET("banner/json")
    suspend fun getBanner(): WanResponse<List<BannerImg>>


}