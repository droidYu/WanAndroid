package com.droidyu.wanandroid.ui.main.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.entity.Tree
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {


    private val _trees = MutableLiveData<WanResult<List<Tree>>>()
    val trees: LiveData<WanResult<List<Tree>>> = _trees

    fun getTree() {
        viewModelScope.launch {
            try {
                val response = repository.getTree()
                if (response.errorCode == 0) {
                    _trees.postValue(WanResult.Success(response.data))
                } else {
                    _trees.postValue(WanResult.Error(response.errorMsg))
                }

            } catch (e: Exception) {
                _trees.postValue(WanResult.Error(e.message.toString()))

            }
        }
    }
}