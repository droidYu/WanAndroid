package com.droidyu.wanandroid.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.entity.Article
import com.droidyu.wanandroid.data.entity.BannerImg
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _articleList = MutableLiveData<WanResult<List<Article>>>()
    val articleList: LiveData<WanResult<List<Article>>> = _articleList

    private val _bannerImgs = MutableLiveData<WanResult<List<BannerImg>>>()
    val bannerImgs: LiveData<WanResult<List<BannerImg>>> = _bannerImgs

    private var nextPage = 0

    private val list = mutableListOf<Article>()

    fun getBanner() {
        viewModelScope.launch {
            try {
                val response = repository.getBanner()
                if (response.errorCode != 0) {
                    _bannerImgs.postValue(WanResult.Error(response.errorMsg))
                }
                _bannerImgs.postValue(WanResult.Success(response.data))
            } catch (e: Exception) {
                _bannerImgs.postValue(WanResult.Error(e.message.toString()))
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                val response = repository.refresh()
                if (response.errorCode != 0) {
                    _articleList.postValue(WanResult.Error(response.errorMsg))
                } else {
                    nextPage = response.data.curPage
                    list.clear()
                    list.addAll(response.data.datas)
                    _articleList.postValue(WanResult.Success(list))
                }
            } catch (e: Exception) {
                _articleList.postValue(WanResult.Error(e.message.toString()))
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            try {
                val response = repository.loadMore(nextPage)
                if (response.errorCode != 0) {
                    _articleList.postValue(WanResult.Error(response.errorMsg))
                } else {
                    nextPage = response.data.curPage
                    list.addAll(response.data.datas)
                    _articleList.postValue(WanResult.Success(list))
                }
            } catch (e: Exception) {
                _articleList.postValue(WanResult.Error(e.message.toString()))
            }
        }
    }
}
