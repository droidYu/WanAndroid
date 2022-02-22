package com.droidyu.wanandroid.ui

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidyu.wanandroid.data.LoginRepository
import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {


    var userName = ObservableField("18314437549")
    var passWord = ObservableField("Zy@941020")

    var response = MutableLiveData<WanResponse<User>>()

    fun login(view: View) {
        viewModelScope.launch {
            response.value = repository.login(userName.get() ?: "", passWord.get() ?: "")
        }
    }
}