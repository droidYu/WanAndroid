package com.droidyu.wanandroid.ui.home

import androidx.lifecycle.ViewModel
import com.droidyu.wanandroid.data.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel()