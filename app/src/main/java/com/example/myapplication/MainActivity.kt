package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if(usuarioAtual != null){
            val progam = Intent(this, despertador::class.java)
            startActivity(progam)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener { view ->
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()

            if(senha.isEmpty() || email.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(email,senha)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val progam = Intent(this, despertador::class.java)
                            startActivity(progam)
                            finish()
                        }
                    }.addOnFailureListener {
                        val snackbar = Snackbar.make(view,"Senha ou E-mail Incorretos!", Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
            }
        }

        binding.textoum.setOnClickListener {
            val cadastro = Intent(this, cadastro::class.java)
            startActivity(cadastro)
        }

    }


}