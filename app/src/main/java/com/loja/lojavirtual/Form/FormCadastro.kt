package com.loja.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.loja.lojavirtual.databinding.ActivityFormCadastroBinding


class FormCadastro : AppCompatActivity() {


    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btnCadastrar.setOnClickListener {
            CadastrarUsuario()
        }


    }
    private fun CadastrarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        
        if(email.isEmpty() || senha.isEmpty()){
          //  Toast.makeText(this, "Preencha seu email e senha", Toast.LENGTH_SHORT).show()
          var snackbar = Snackbar.make(binding.layoutCadastro, "Coloque seu E-mail e senha!!",
              Snackbar.LENGTH_INDEFINITE)
           snackbar.setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {
           })
            snackbar.show()

        } 
        else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
                if(it.isSuccessful){
                    var snackbar = Snackbar.make(binding.layoutCadastro, "Cadastro realizado com sucesso!!",
                        Snackbar.LENGTH_INDEFINITE)
                    snackbar.setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {
                        VoltarFormLogin()
                    })

                    snackbar.show()
                }
            }.addOnFailureListener {
                var snackbar = Snackbar.make(binding.layoutCadastro, "Erro ao cadastrar usuario, Verifique!!",
                    Snackbar.LENGTH_INDEFINITE)
                snackbar.setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {
                })
                snackbar.show()

            }
            
        }
    }

    private fun VoltarFormLogin(){
        var intent = Intent(this,FormLogin::class.java)
        startActivity(intent)
        finish()
    }

    private fun ClearFields(){
        binding.editEmail.setText("")
        binding.editSenha.setText("")
    }
}