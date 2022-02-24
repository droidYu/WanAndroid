package com.droidyu.wanandroid.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidyu.wanandroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment()).commit()
    }
}