package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityVisualizacaoDadosUserBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class visualizacao_dados_user : AppCompatActivity() {
    private lateinit var binding: ActivityVisualizacaoDadosUserBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizacaoDadosUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = auth.currentUser?.email.toString()
        db.collection("Usuarios").document(email)
            .addSnapshotListener{documento, error ->
                if(documento != null){
                    binding.inputName.hint = documento.getString("nome")
                    binding.textView3.text = "Email: "+documento.getString("email")
                    binding.inputEndereco.hint = documento.getString("endereco")
                    binding.inputTelefone.hint = documento.getString("contato")
                }
            }

        binding.excluir.setOnClickListener{
            db.collection("Usuarios").document(email)
                .delete().addOnCompleteListener {
                    auth.currentUser?.delete()
                    FirebaseAuth.getInstance().signOut()
                    val inten = Intent(this, MainActivity::class.java)
                    startActivity(inten)
                    finish()
                }
        }

        binding.alterarBTN.setOnClickListener {view ->
            var nome = binding.inputName.text.toString()
            var endereco = binding.inputEndereco.text.toString()
            var telefone = binding.inputTelefone.text.toString()

            if(nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }else{
                db.collection("Usuarios").document(email).update("endereco",endereco)
                db.collection("Usuarios").document(email).update("contato", telefone)
                db.collection("Usuarios").document(email).update("nome",nome).addOnCompleteListener {
                    val snackbar = Snackbar.make(view, "Foi alterado com sucesso!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        }

        binding.voltar.setOnClickListener {
            val intent = Intent(this, despertador::class.java)
            startActivity(intent)
        }
    }
}