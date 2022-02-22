package com.droidyu.wanandroid.network

import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("user/login")
    suspend fun login(
        @Query("username") userName: String,
        @Query("password") passWord: String
    ): WanResponse<User>


}