

package com.example.mind

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mind.models.Users
import com.example.mind.providers.UserProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class registro : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mTextInputNombre: TextInputEditText
    private lateinit var mTextInputTelefono: TextInputEditText
    private lateinit var mTextInputEmail: TextInputEditText
    private lateinit var mTextInputPassword: TextInputEditText
    private lateinit var mUserProvider: UserProvider

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()
        mUserProvider = UserProvider()

        mTextInputNombre = findViewById(R.id.etNombre)
        mTextInputTelefono = findViewById(R.id.etTelefono)
        mTextInputEmail = findViewById(R.id.etEmail)
        mTextInputPassword = findViewById(R.id.etPassword)

        val btnRetro: ImageView = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener{
            retroceso()
        }
        val btnCancelar: Button = findViewById(R.id.btnCancelar)
        btnCancelar.setOnClickListener {
            onCancelButtonClicked()
        }

        //Boton Aceptar
        val btnAceptar: Button = findViewById(R.id.btnRegistrar)
        btnAceptar.setOnClickListener {
            registroUsuario()
        }
        //Boton terminos
        val tvTerminos: TextView = findViewById(R.id.tvTerminos)
        tvTerminos.setOnClickListener{
            Terminos()
        }
    }

    private fun registroUsuario (){
        val name      : String = mTextInputNombre.text.toString()
        val email     : String = mTextInputEmail.text.toString()
        val password  : String  = mTextInputPassword.text.toString()
        val telefono : String  = mTextInputTelefono.text.toString()

        if (name.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su nombre",Toast.LENGTH_SHORT).show()
        }
        if (email.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su correo",Toast.LENGTH_SHORT).show()
        }
        if (password.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su contraseña",Toast.LENGTH_SHORT).show()
        }
        if (telefono.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su telefono",Toast.LENGTH_SHORT).show()
        }

        if(name.isNotEmpty() && telefono.isNotEmpty() && email.isNotEmpty()  && password.isNotEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) {
                        var uid : String = ""
                        uid = mAuth.currentUser!!.uid
                        var reference =
                            FirebaseDatabase.getInstance().reference.child("Users").child(uid)
                        val hashMap = HashMap<String, Any>()
                        val hname : String = mTextInputNombre.text.toString()
                        val htelefono : String = mTextInputTelefono.text.toString()
                        val hemail : String = mTextInputEmail.text.toString()
                        val hpassword : String = mTextInputPassword.text.toString()

                        //hashMap["uid"] = uid
                        hashMap["f_nombre"] = hname
                        hashMap["f_telefono"] = htelefono
                        hashMap["f_emai"] = hemail
                        hashMap["f_password"] = hpassword
                        hashMap["buscar"] = hname.lowercase()

                        reference.updateChildren(hashMap).addOnCompleteListener { task2->
                            if(task2.isSuccessful){
                                val intent = Intent(this@registro, Terminos::class.java)
                                Toast.makeText(this,"Se logró registrar con éxito",Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                            }
                        }
                    } else {
                        var test = it.exception?.message
                        Toast.makeText(this,"No se logro registrar " + test,Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
}
    //funcion retroceso
    private fun retroceso() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    //boton cancelar
    fun onCancelButtonClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
    }
}