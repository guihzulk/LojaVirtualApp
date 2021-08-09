package com.loja.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.loja.lojavirtual.R
import com.loja.lojavirtual.TelaPrincipal
import com.loja.lojavirtual.databinding.ActivityFormLoginBinding


class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
       binding.txtCadastro.setOnClickListener {
            var intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
        binding.btnEntrar.setOnClickListener {
            AutenticarUsuario()
        }

    }
    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if(email.isEmpty() || senha.isEmpty()){
            var snackbar = Snackbar.make(binding.layoutLogin,"Coloque um E-mail e senha!!", Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                .setAction("ok", View.OnClickListener {
                })
            snackbar.show()
        }else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
                if (it.isSuccessful) {
                    AbrirTelaPrincipal()
                }
            }.addOnFailureListener {
                var snackbar = Snackbar.make(binding.layoutLogin,"Erro ao logar o usuario!!", Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)

                snackbar.show()
            }
        }

    }
    private fun AbrirTelaPrincipal(){
        var intent = Intent(this,TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }
}