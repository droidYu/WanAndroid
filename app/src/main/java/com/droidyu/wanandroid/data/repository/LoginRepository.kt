package com.droidyu.wanandroid.data.repository

import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import com.droidyu.wanandroid.network.Api
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: Api,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend fun login(userName: String, passWord: String): WanResponse<User> {
        return withContext(defaultDispatcher) {
            api.login(userName, passWord)

        }
    }
}