package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityEscolhaBinding

class escolha : AppCompatActivity() {

    lateinit var binding: ActivityEscolhaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEscolhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val inten = Intent(this, cadastro_animal::class.java)
            startActivity(inten)
        }
    }
}