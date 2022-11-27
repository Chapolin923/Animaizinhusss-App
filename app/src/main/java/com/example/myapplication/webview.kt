package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.myapplication.databinding.ActivityWebviewBinding

class webview : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWeb()

        binding.voltarum.setOnClickListener {
            val intent = Intent(this, despertador::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWeb() {
        binding.webview.webViewClient = WebViewClient()
        binding.webview.apply {
            loadUrl("https://animaizinhusss.netlify.app/")
            settings.javaScriptEnabled = true
        }
    }
}