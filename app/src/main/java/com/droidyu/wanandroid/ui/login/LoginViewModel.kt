package com.droidyu.wanandroid.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.LoginRepository
import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    var userName = ObservableField("18314437549")
    var passWord = ObservableField("Zy@941020")

    private var _response = MutableLiveData<WanResult<User>>()
    var response: LiveData<WanResult<User>> = _response

    fun login() {
        viewModelScope.launch {
            try {
                val response = repository.login(userName.get() ?: "", passWord.get() ?: "")
                if (response.errorCode == 0) {
                    _response.postValue(WanResult.Success(response.data))
                } else {
                    _response.postValue(WanResult.Error(response.errorMsg))
                }
            } catch (e: Exception) {
                _response.postValue(WanResult.Error(e.message.toString()))
            }
        }
    }
}