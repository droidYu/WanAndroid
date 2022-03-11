package com.droidyu.wanandroid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.entity.Article
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(val repository: ArticleRepository) : ViewModel() {
    private val _articleList = MutableLiveData<WanResult<List<Article>>>()
    val articleList: LiveData<WanResult<List<Article>>> = _articleList

    private var nextPage = 0

    private val list = mutableListOf<Article>()

    fun refresh(cId: String) {
        viewModelScope.launch {
            try {
                val response = repository.refresh(cId)
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

    fun loadMore(cId: String) {
        viewModelScope.launch {
            try {
                val response = repository.loadMore(nextPage, cId)
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