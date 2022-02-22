package com.droidyu.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHomeBinding?>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply { vm = viewModel }
        return binding.root
    }

}