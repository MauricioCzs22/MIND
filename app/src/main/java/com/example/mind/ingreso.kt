package com.example.mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ingreso : AppCompatActivity() {

    private lateinit var txRegis: TextView

   private lateinit var mTextInputEmail : TextInputEditText
   private lateinit var mTextInputPassword: TextInputEditText
   private lateinit var mAuth: FirebaseAuth
   private lateinit var mDatabase: DatabaseReference
   private lateinit var btnLogin:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)
        //INSTANCIAS
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()

        //enlaces
        mTextInputEmail = findViewById(R.id.textInputEmail)
        mTextInputPassword = findViewById(R.id.textInputPassword)


        //BOTONES
        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login()
        }
        //boton retorno
        val btnRetro: ImageView = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener{
            onBackPressed()
        }
        //boton cancelar
        val btnCancelar: Button = findViewById(R.id.btnCancelar)
        btnCancelar.setOnClickListener {
            onCancelButtonClicked()
        }
        //enlace
        txRegis = findViewById(R.id.txRegis)
        txRegis.setOnClickListener {
            registro()
        }
    }
     //FUNCION LOGIN
     private fun login() {
         val email = mTextInputEmail.text.toString()
         val password = mTextInputPassword.text.toString()

         if (email.isNotEmpty() && password.isNotEmpty()) {
             FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener {
                         if (it.isSuccessful) {
                             val intent1: Intent =
                                 Intent(applicationContext, Terminos::class.java).apply {
                                     putExtra("email", it.result?.user?.email ?: "")
                                 }
                             startActivity(intent1)
                         } else {
                             Toast.makeText(this, "La contraseña o el password son incorrectos", Toast.LENGTH_SHORT).show()
                         }
                     }
         }
     }

    //funcion retroceso
    override fun onBackPressed() {
        super.onBackPressed()
    }
    //boton cancelar
    fun onCancelButtonClicked() {
        Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
    }
    private fun registro(){
        val intent = Intent(this, registro::class.java)
        startActivity(intent)
    }
}