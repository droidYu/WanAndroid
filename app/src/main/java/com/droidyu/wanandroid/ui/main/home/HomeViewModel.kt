package com.droidyu.wanandroid.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.entity.BannerImg
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {


    private val _bannerImgs = MutableLiveData<WanResult<List<BannerImg>>>()
    val bannerImgs: LiveData<WanResult<List<BannerImg>>> = _bannerImgs


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


}
