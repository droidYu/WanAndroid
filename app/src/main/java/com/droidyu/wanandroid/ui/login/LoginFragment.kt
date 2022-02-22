package com.droidyu.wanandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.entity.WanResult
import com.droidyu.wanandroid.databinding.FragmentLoginBinding
import com.droidyu.wanandroid.ui.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        ).apply {
            vm = viewModel
            viewModel.response.observe(viewLifecycleOwner) { response ->
                (context)?.let {
                    showToast(
                        it, when (response) {
                            is WanResult.Success -> "登录成功：${response.data.nickname}"
                            is WanResult.Error -> "登录失败：${response.errorMsg}"
                        }
                    )
                }
            }
        }.root
    }

}