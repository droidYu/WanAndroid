package com.droidyu.wanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidyu.wanandroid.ui.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment()).commit()

    }
}