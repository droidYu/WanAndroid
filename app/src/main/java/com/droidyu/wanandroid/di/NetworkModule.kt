package com.droidyu.wanandroid.di

import android.util.Log
import com.droidyu.wanandroid.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@InstallIn(ActivityRetainedComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    fun provideApi(): Api {
        return provideRetrofit().create(Api::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .baseUrl("https://www.wanandroid.com/")
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val response = chain.proceed(chain.request())
                Log.d("network", response.networkResponse().toString())
                response.newBuilder().build()
            }
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}