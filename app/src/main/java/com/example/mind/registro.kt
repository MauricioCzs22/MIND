package com.example.mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class registro : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()

        mDatabase = FirebaseDatabase.getInstance().getReference()




        //BOTONES
        //Boton retroceso
        val btnRetro: ImageView = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener{
            onBackPressed()
        }
        //Boton Cancelar
        val btnCancelar: Button = findViewById(R.id.btnCancelar)
        btnCancelar.setOnClickListener {
            onCancelButtonClicked()
        }
        //Boton Aceptar
        val btnAceptar: Button = findViewById(R.id.btnAceptar)
        btnAceptar.setOnClickListener {
            PrincipalView()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    //boton cancelar
    fun onCancelButtonClicked() {
        Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
    }
    private fun PrincipalView(){
        val intent = Intent(this, PrincipalView::class.java)
        startActivity(intent)
    }

}