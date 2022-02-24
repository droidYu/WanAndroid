package com.droidyu.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.HomeRepository
import com.droidyu.wanandroid.data.entity.Article
import com.droidyu.wanandroid.data.entity.WanResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _articleList = MutableLiveData<WanResult<List<Article>>>()
    val articleList: LiveData<WanResult<List<Article>>> = _articleList
    private val page = 0

    fun refresh() {
        viewModelScope.launch {
            try {
                _articleList.postValue(WanResult.Success(repository.refresh().data.datas))
            } catch (e: Exception) {
                _articleList.postValue(WanResult.Error(e.message.toString()))
            }
        }
    }

    fun loadMore(page: Int) {
        viewModelScope.launch {
            try {
                _articleList.postValue(WanResult.Success(repository.loadMore(page).data.datas))
            } catch (e: Exception) {
                _articleList.postValue(WanResult.Error(e.message.toString()))
            }
        }
    }
}
