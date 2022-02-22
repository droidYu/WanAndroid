package com.droidyu.wanandroid.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.LoginRepository
import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    var userName = ObservableField("18314437549")
    var passWord = ObservableField("Zy@941020")

    private var _response = MutableLiveData<WanResponse<User>>()
    var response: LiveData<WanResponse<User>> = _response

    fun login() {
        viewModelScope.launch {
            try {
                val response = repository.login(userName.get() ?: "", passWord.get() ?: "")
                _response.postValue(response)
            } catch (e: Exception) {

            }
        }
    }
}