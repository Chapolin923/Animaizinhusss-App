package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCadastroCuidados2Binding
import com.example.myapplication.databinding.ActivityCadastroCuidados3Binding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class cadastro_cuidados3 : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroCuidados3Binding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroCuidados3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { view ->
            var id = binding.id.text.toString()
            var especi = binding.especificacao.text.toString()
            if(id.isEmpty() || especi.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }else{
                val cuidado = hashMapOf(
                    "id" to id,
                    "especificacao" to especi
                )
                val email = auth.currentUser?.email.toString()
                db.collection("Usuarios").document(email)
                    .addSnapshotListener{documento, error ->
                        if (documento != null) {
                            db.collection("Animais").document(documento.getString("pet1").toString()).update("cuidado3", id)
                        }
                    }
                db.collection("Cuidados").document(id).set(cuidado)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this, despertador::class.java)
                            startActivity(intent)
                        }
                    }.addOnFailureListener {
                        val snackbar = Snackbar.make(view,"Erro ao criar!", Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
            }
        }
    }
}