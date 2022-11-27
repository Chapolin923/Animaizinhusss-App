package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCadastroAnimalBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class cadastro_animal : AppCompatActivity() {

    lateinit var binding: ActivityCadastroAnimalBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener { view ->
            val id = binding.cadastrarID.text.toString()
            val email = auth.currentUser?.email.toString()
            if(id.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha o campo Definir ID!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }else{
                db.collection("Usuarios").document(email).update("pet1",id)
                val inten = Intent(this, despertador::class.java)
                startActivity(inten)
            }
        }
        binding.button.setOnClickListener { view ->

            val email = auth.currentUser?.email.toString()
            val nome = binding.editTextTextPersonName.text.toString()
            val idade = binding.editTextTextPersonName2.text.toString()
            val raca = binding.editTextTextPersonName3.text.toString()
            val peso = binding.editTextTextPersonName4.text.toString()
            val id = binding.editTextTextPersonName5.text.toString()

            if (peso.isEmpty() || raca.isEmpty() || idade.isEmpty() || nome.isEmpty()) {
                val snackbar = Snackbar.make(view,"Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val animal = hashMapOf(
                    "nome" to nome,
                    "idade" to idade,
                    "raca" to raca,
                    "peso" to peso,
                    "cuidado1" to "999",
                    "cuidado2" to "999",
                    "cuidado3" to "999",
                )
                db.collection("Usuarios").document(email).update("pet1",id)
                db.collection("Animais").document(id).set(animal)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            val inten = Intent(this, despertador::class.java)
                            startActivity(inten)
                        }
                    }.addOnFailureListener {
                        val snackbar = Snackbar.make(view,"Não é possivel criar mais animais!", Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
                }
            }
        }
}