package com.droidyu.wanandroid.ui.main.article.detail

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.droidyu.wanandroid.R

class ArticleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        supportActionBar?.hide()

        val link = intent.extras?.getString("link")

        val webView = findViewById<WebView>(R.id.web)
        webView.loadUrl(link.toString())

    }
}