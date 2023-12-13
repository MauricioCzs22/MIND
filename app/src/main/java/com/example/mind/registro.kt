

package com.example.mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mind.models.Users
import com.example.mind.providers.UserProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class registro : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mTextInputNombre: TextInputEditText
    private lateinit var mTextInputMatricula: TextInputEditText
    private lateinit var mTextInputEmail: TextInputEditText
    private lateinit var mTextInputSemestre: AutoCompleteTextView
    private lateinit var mTextInputCarrera: AutoCompleteTextView
    private lateinit var mTextInputPassword: TextInputEditText

    private val db = FirebaseFirestore.getInstance()

    private lateinit var mUserProvider: UserProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()
        mUserProvider = UserProvider()

        //enlaces
        mTextInputNombre = findViewById(R.id.etNombre)
        mTextInputMatricula = findViewById(R.id.etMatricula)
        mTextInputEmail = findViewById(R.id.etEmail)
        mTextInputSemestre = findViewById(R.id.etSemestre)
        mTextInputCarrera = findViewById(R.id.Etcarrera)
        mTextInputPassword = findViewById(R.id.etPassword)

        //BOTONES
        //Boton retroceso
        val btnRetro: ImageView = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener{
            onBackPressed()
        }
        //Boton Cancelar
        val btnCancelar: Button = findViewById(R.id.btnRCancelar)
        btnCancelar.setOnClickListener {
            onCancelButtonClicked()
        }
        //Boton Aceptar
        val btnAceptar: Button = findViewById(R.id.btnRegistrar)
        btnAceptar.setOnClickListener {
            registroUsuario()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    //boton cancelar
    fun onCancelButtonClicked() {
        Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
    }
    private fun registroUsuario (){
        val name = mTextInputNombre.text.toString()
        val email = mTextInputEmail.text.toString()
        val password = mTextInputPassword.text.toString()
        val matricula = mTextInputMatricula.text.toString()
        val semestre = mTextInputSemestre.text.toString()
        val carrera = mTextInputCarrera.text.toString()


        if(name.isNotEmpty() && matricula.isNotEmpty() && email.isNotEmpty() && semestre.isNotEmpty() && carrera.isNotEmpty() && password.isNotEmpty()){
       // if(name.isNotEmpty() && email.isNotEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) {
                        var idUser = mAuth.currentUser?.uid;
                        val userNew = Users(idUser.toString(), name, email, password, matricula, semestre, carrera);
                        saveUser(userNew)
                    } else {

                        var test = it.exception?.message
                        Toast.makeText(this,"No se logro registrar " + test,Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

    }

    private fun saveUser(userNew: Users) {        mUserProvider.create(userNew)?.addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Se logró registrar en la base de datos",Toast.LENGTH_SHORT).show()
                } else {

                    var test = it.exception?.message
                    Toast.makeText(this,"No se logró registrar en la base de datos" + test,Toast.LENGTH_SHORT).show()
                }
            }

        )
    }

    /*private fun isFormularioValido(): Boolean {
        var flag = true;
        var name = mTextInputNombre.text.toString()
        if (name.isEmpty()) flag = false;
        if (!matricula.isNotEmpty()) flag = false;
        if (!email.isNotEmpty()) flag = false;
        if (!semestre.isNotEmpty()) flag = false;
        if (!carrera.isNotEmpty()) flag = false;
        if (!password.isNotEmpty()) flag = false;

        return flag;
    }*/
    /*
        */

}