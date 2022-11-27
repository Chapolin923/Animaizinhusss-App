package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMain2Binding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class cadastro: AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img.setOnClickListener{
            pickImage()
        }

        binding.criarConta.setOnClickListener { view ->

            val email = binding.emailConjunto.text.toString()
            val senha = binding.senhaConjunto.text.toString()
            val confsenha = binding.senhaConfirmaConjunto.text.toString()
            val nome = binding.nomeConjunto.text.toString()
            val endereco = binding.endereco.text.toString()
            val contato = binding.contato.text.toString()

            if(senha.isEmpty() || email.isEmpty() || confsenha.isEmpty() || nome.isEmpty() || endereco.isEmpty() || contato.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }else{
                if(confsenha != senha){
                    val snackbar = Snackbar.make(view,"As senhas estão diferentes!", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }else{
                    auth.createUserWithEmailAndPassword(email,senha)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                val usuario = hashMapOf(
                                    "nome" to nome,
                                    "email" to email,
                                    "endereco" to endereco,
                                    "contato" to contato,
                                    "pet1" to "999",
                                    "pet2" to "999",
                                    "pet3" to "999"
                                )
                                db.collection("Usuarios").document(email).set(usuario)
                                    .addOnCompleteListener{
                                        if(it.isSuccessful){
                                            val intent = Intent(this, MainActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                    }.addOnFailureListener {
                                        val snackbar = Snackbar.make(view, "Erro no Banco", Snackbar.LENGTH_SHORT)
                                        snackbar.show()
                                    }
                            }
                        }.addOnFailureListener { exception ->
                            val mensagemErro = when(exception){
                                is FirebaseAuthWeakPasswordException -> "Senha com menos de 6 caracteres"
                                is FirebaseAuthInvalidCredentialsException -> "Email invalido!"
                                is FirebaseAuthUserCollisionException -> "Conta já cadastrada!"
                                is FirebaseNetworkException -> "Sem conexão com a internet!"
                                else -> "Erro ao cadastrar"
                            }
                            val snackbar = Snackbar.make(view, mensagemErro, Snackbar.LENGTH_SHORT)
                            snackbar.show()
                        }
                }
            }
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.img.setImageURI(data?.data)
        }
    }
}