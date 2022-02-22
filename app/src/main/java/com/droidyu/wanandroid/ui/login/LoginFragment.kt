package com.droidyu.wanandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.databinding.FragmentLoginBinding
import com.droidyu.wanandroid.ui.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onResume() {
        super.onResume()
        viewModel.response.observe(this) { response ->
            if (response.errorCode == 0) {
                (context)?.let { showToast(it, "success") }
            } else {
                context?.let { showToast(it, response.errorMsg) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        ).apply {
            vm = viewModel
        }
        return binding.root
    }

}