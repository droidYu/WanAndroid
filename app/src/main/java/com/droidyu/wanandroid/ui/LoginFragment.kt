package com.droidyu.wanandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.data.LoginRepository
import com.droidyu.wanandroid.data.entity.User
import com.droidyu.wanandroid.data.entity.WanResponse
import com.droidyu.wanandroid.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var repository: LoginRepository
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            LoginViewModelFactory(repository)
        )[LoginViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        viewModel.response.observe(this, object : Observer<WanResponse<User>> {
            override fun onChanged(response: WanResponse<User>) {
                if (response.errorCode == 0) {
                    (context)?.let { showToast(it, "success") }
                } else {
                    context?.let { showToast(it, response.errorMsg) }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding = DataBindingUtil.bind<FragmentLoginBinding>(view)?.apply {
            vm = viewModel
        }!!
        return view
    }

}