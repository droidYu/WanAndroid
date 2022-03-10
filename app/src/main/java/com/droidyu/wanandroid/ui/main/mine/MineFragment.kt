package com.droidyu.wanandroid.ui.main.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droidyu.wanandroid.R
import com.droidyu.wanandroid.databinding.FragmentMineBinding

class MineFragment : Fragment() {

    private val viewModel: MineViewModel by lazy { ViewModelProvider(this)[MineViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentMineBinding>(
            inflater,
            R.layout.fragment_mine,
            container,
            false
        ).apply {
            vm = viewModel
            tv.setOnClickListener {
                findNavController().navigate(R.id.action_mineFragment_to_loginActivity)
            }
        }.root
    }
}