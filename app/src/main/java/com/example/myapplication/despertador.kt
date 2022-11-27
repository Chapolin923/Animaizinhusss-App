package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDespertadorBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class despertador : AppCompatActivity() {

    lateinit var binding: ActivityDespertadorBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDespertadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = auth.currentUser?.email.toString()

        db.collection("Usuarios").document(email)
            .addSnapshotListener{documento, error ->
                if (documento != null) {
                    db.collection("Animais").document(documento.getString("pet1").toString())
                        .addSnapshotListener{documente, erro ->
                            if (documente != null) {
                                binding.nome1.text = documente.getString("nome")+" - "+documente.getString("raca")+" - "+documente.getString("idade")+" anos"
                            }
                        }
                    db.collection("Animais").document(documento.getString("pet2").toString())
                        .addSnapshotListener{documente, erro ->
                            if (documente != null) {
                                binding.nome2.text = documente.getString("nome")+" - "+documente.getString("raca")+" - "+documente.getString("idade")+" anos"
                            }
                        }
                    db.collection("Animais").document(documento.getString("pet3").toString())
                        .addSnapshotListener{documente, erro ->
                            if (documente != null) {
                                binding.nome3.text = documente.getString("nome")+" - "+documente.getString("raca")+" - "+documente.getString("idade")+" anos"
                            }
                        }
                }
            }

        db.collection("Usuarios").document(email)
            .addSnapshotListener{ documt, er ->
                if(documt != null){
                    db.collection("Animais").document(documt.getString("pet1").toString())
                        .addSnapshotListener{documento, error ->
                            if (documento != null) {
                                val id1 = documento.getString("cuidado1")
                                val id2 = documento.getString("cuidado2")
                                val id3 = documento.getString("cuidado3")
                                if (id1 != null) {
                                    db.collection("Cuidados").document(id1)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado1.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                                if (id2 != null) {
                                    db.collection("Cuidados").document(id2)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado2.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                                if (id3 != null) {
                                    db.collection("Cuidados").document(id3)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado3.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                            }
                        }
                }
            }

        db.collection("Usuarios").document(email)
            .addSnapshotListener{ documt, er ->
                if(documt != null){
                    db.collection("Animais").document(documt.getString("pet2").toString())
                        .addSnapshotListener{documento, error ->
                            if (documento != null) {
                                val id1 = documento.getString("cuidado1")
                                val id2 = documento.getString("cuidado2")
                                val id3 = documento.getString("cuidado3")
                                if (id1 != null) {
                                    db.collection("Cuidados").document(id1)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado21.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                                if (id2 != null) {
                                    db.collection("Cuidados").document(id2)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado22.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                                if (id3 != null) {
                                    db.collection("Cuidados").document(id3)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado23.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                            }
                        }
                }
            }

        db.collection("Usuarios").document(email)
            .addSnapshotListener{ documt, er ->
                if(documt != null){
                    db.collection("Animais").document(documt.getString("pet3").toString())
                        .addSnapshotListener{documento, error ->
                            if (documento != null) {
                                val id1 = documento.getString("cuidado1")
                                val id2 = documento.getString("cuidado2")
                                val id3 = documento.getString("cuidado3")
                                if (id1 != null) {
                                    db.collection("Cuidados").document(id1)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado31.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                                if (id2 != null) {
                                    db.collection("Cuidados").document(id2)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado32.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                                if (id3 != null) {
                                    db.collection("Cuidados").document(id3)
                                        .addSnapshotListener{documente, erro ->
                                            if (documente != null) {
                                                binding.cuidado33.text = documente.getString("especificacao")
                                            }
                                        }
                                }
                            }
                        }
                }
            }


        binding.criarCuidado1.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados::class.java)
            startActivity(intent)
        }
        binding.criarCuidado2.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados2::class.java)
            startActivity(intent)
        }
        binding.criarCuidado3.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados3::class.java)
            startActivity(intent)
        }
        binding.criarCuidado21.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados4::class.java)
            startActivity(intent)
        }
        binding.criarCuidado22.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados5::class.java)
            startActivity(intent)
        }
        binding.criarCuidado23.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados6::class.java)
            startActivity(intent)
        }
        binding.criarCuidado31.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados7::class.java)
            startActivity(intent)
        }
        binding.criarCuidado32.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados8::class.java)
            startActivity(intent)
        }
        binding.criarCuidado33.setOnClickListener {
            val intent = Intent(this, cadastro_cuidados9::class.java)
            startActivity(intent)
        }






        binding.alterarAnimal1.setOnClickListener {
            val intent = Intent(this, alterar_animal::class.java)
            startActivity(intent)
        }
        binding.alterarAnimal2.setOnClickListener {
            val intent = Intent(this, alterar_animal2::class.java)
            startActivity(intent)
        }
        binding.alterarAnimal3.setOnClickListener {
            val intent = Intent(this, alterar_animal3::class.java)
            startActivity(intent)
        }









        binding.excluirCuidado1.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet1").toString()).update("cuidado1", "999")
                    }
                }
        }
        binding.excluirCuidado2.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet1").toString()).update("cuidado2", "999")
                    }
                }
        }
        binding.excluirCuidado3.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet1").toString()).update("cuidado3", "999")
                    }
                }
        }







        binding.excluirCuidado21.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet2").toString()).update("cuidado1", "999")
                    }
                }
        }
        binding.excluirCuidado22.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet2").toString()).update("cuidado2", "999")
                    }
                }
        }
        binding.excluirCuidado23.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet2").toString()).update("cuidado3", "999")
                    }
                }
        }











        binding.excluirCuidado31.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet3").toString()).update("cuidado1", "999")
                    }
                }
        }
        binding.excluirCuidado32.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet3").toString()).update("cuidado2", "999")
                    }
                }
        }
        binding.excluirCuidado33.setOnClickListener {
            db.collection("Usuarios").document(email)
                .addSnapshotListener{documento, error ->
                    if(documento != null){
                        db.collection("Animais").document(documento.getString("pet3").toString()).update("cuidado3", "999")
                    }
                }
        }




        binding.criarAnimal1.setOnClickListener {
            val inten = Intent(this, cadastro_animal::class.java)
            startActivity(inten)
        }
        binding.criarAnimal2.setOnClickListener {
            val inten = Intent(this, cadastro_animal2::class.java)
            startActivity(inten)
        }
        binding.criarAnimal3.setOnClickListener {
            val inten = Intent(this, cadastro_animal3::class.java)
            startActivity(inten)
        }





        binding.alterar.setOnClickListener {
            val inten = Intent(this, visualizacao_dados_user::class.java)
            startActivity(inten)
        }

        binding.webview.setOnClickListener {
            val inten = Intent(this, webview::class.java)
            startActivity(inten)
        }

        binding.voltar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
            val inten = Intent(this, MainActivity::class.java)
            startActivity(inten)
        }
    }
}