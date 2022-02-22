package com.droidyu.wanandroid.data

import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import com.droidyu.wanandroid.network.Api
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRepository @Inject constructor() {
    @Inject
    lateinit var retrofit: Retrofit
    suspend fun login(userName: String, passWord: String): WanResponse<User> {
        return retrofit.create(Api::class.java)
            .login(userName, passWord)
    }
}