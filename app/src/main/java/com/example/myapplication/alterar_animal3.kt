package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAlterarAnimal2Binding
import com.example.myapplication.databinding.ActivityAlterarAnimal3Binding
import com.example.myapplication.databinding.ActivityAlterarAnimalBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class alterar_animal3 : AppCompatActivity() {
    private lateinit var binding: ActivityAlterarAnimal3Binding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlterarAnimal3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = auth.currentUser?.email.toString()

        db.collection("Usuarios").document(email)
            .addSnapshotListener{documento, error ->
                if(documento != null){
                    db.collection("Animais").document(documento.getString("pet3").toString())
                        .addSnapshotListener{ documente, err ->
                            if(documente != null){
                                binding.editTextTextPersonName.hint = documente.getString("nome")
                                binding.editTextTextPersonName2.hint = documente.getString("idade")
                                binding.editTextTextPersonName3.hint = documente.getString("raca")
                                binding.editTextTextPersonName4.hint = documente.getString("peso")
                            }
                        }
                }
            }

        binding.button.setOnClickListener {view ->
            var nome = binding.editTextTextPersonName.text.toString()
            var idade = binding.editTextTextPersonName2.text.toString()
            var raca = binding.editTextTextPersonName3.text.toString()
            var peso = binding.editTextTextPersonName4.text.toString()

            if(idade.isEmpty() || nome.isEmpty() || raca.isEmpty() || peso.isEmpty()) {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }else{
                db.collection("Usuarios").document(email)
                    .addSnapshotListener{documento, error ->
                        if(documento != null){
                            db.collection("Animais").document(documento.getString("pet3").toString()).update("nome",nome)
                        }
                    }
                db.collection("Usuarios").document(email)
                    .addSnapshotListener{documento, error ->
                        if(documento != null){
                            db.collection("Animais").document(documento.getString("pet3").toString()).update("idade",idade)
                        }
                    }
                db.collection("Usuarios").document(email)
                    .addSnapshotListener{documento, error ->
                        if(documento != null){
                            db.collection("Animais").document(documento.getString("pet3").toString()).update("raca",raca)
                        }
                    }
                db.collection("Usuarios").document(email)
                    .addSnapshotListener{documento, error ->
                        if(documento != null){
                            db.collection("Animais").document(documento.getString("pet3").toString()).update("peso",peso)
                                .addOnCompleteListener {
                                    val snackbar = Snackbar.make(view, "Foi alterado com sucesso!", Snackbar.LENGTH_SHORT)
                                    snackbar.show()
                                }
                        }
                    }
            }
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, despertador::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            db.collection("Usuarios").document(email).update("pet3","999")
            val intent = Intent(this, despertador::class.java)
            startActivity(intent)
        }

    }
}