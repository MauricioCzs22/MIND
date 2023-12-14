

package com.example.mind

import android.content.Intent
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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class registro : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mTextInputNombre: TextInputEditText
    private lateinit var mTextInputMatricula: TextInputEditText
    private lateinit var mTextInputEmail: TextInputEditText
    private lateinit var mTextInputSemestre: AutoCompleteTextView
    private lateinit var mTextInputCarrera: AutoCompleteTextView
    private lateinit var mTextInputPassword: TextInputEditText
    private lateinit var mUserProvider: UserProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()
        mUserProvider = UserProvider()

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
        val name      : String = mTextInputNombre.text.toString()
        val email     : String = mTextInputEmail.text.toString()
        val password  : String  = mTextInputPassword.text.toString()
        val matricula : String  = mTextInputMatricula.text.toString()
        val semestre  : String  = mTextInputSemestre.text.toString()
        val carrera   : String  = mTextInputCarrera.text.toString()

        if (name.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su nombre",Toast.LENGTH_SHORT).show()
        }
        if (email.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su correo",Toast.LENGTH_SHORT).show()
        }
        if (password.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su contraseña",Toast.LENGTH_SHORT).show()
        }
        if (matricula.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su matricula",Toast.LENGTH_SHORT).show()
        }
        if (semestre.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese semestre",Toast.LENGTH_SHORT).show()
        }
        if (carrera.isEmpty()){
            Toast.makeText(applicationContext, "Ingrese su carrera",Toast.LENGTH_SHORT).show()
        }

        if(name.isNotEmpty() && matricula.isNotEmpty() && email.isNotEmpty() && semestre.isNotEmpty() && carrera.isNotEmpty() && password.isNotEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) {
                        var uid : String = ""
                        //var idUser = mAuth.currentUser?.uid
                        uid = mAuth.currentUser!!.uid
                        var reference =
                            FirebaseDatabase.getInstance().reference.child("Users").child(uid)
                        //val userNew = Users(idUser.toString(), name, email, password, matricula, semestre, carrera);
                        //saveUser(userNew)
                         val hashMap = HashMap<String, Any>()

                        val hname : String = mTextInputNombre.text.toString()
                        val hmatricula : String = mTextInputMatricula.text.toString()
                        val hemail : String = mTextInputEmail.text.toString()
                        val hsemestre : String = mTextInputSemestre.text.toString()
                        val hcarrera : String = mTextInputCarrera.text.toString()
                        val hpassword : String = mTextInputPassword.text.toString()

                        hashMap["uid"] = uid
                        hashMap["f_nombre"] = hname
                        hashMap["f_matricul"] = hmatricula
                        hashMap["f_emai"] = hemail
                        hashMap["f_semestre"] = hsemestre
                        hashMap["f_carrera"] = hcarrera
                        hashMap["f_password"] = hpassword
                        hashMap["buscar"] = hname.lowercase()

                        reference.updateChildren(hashMap).addOnCompleteListener { task2->
                            if(task2.isSuccessful){
                                val intent = Intent(this@registro, cuenta::class.java)
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
        /*
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
    }*/

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

}}