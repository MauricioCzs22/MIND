package com.example.mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnIngresar: Button
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //enlace boton ingresar
        btnIngresar = findViewById(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            ingreso()
        }
        //enlace boton registrar
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            registro()
        }
    }
    private fun ingreso(){
        val intent = Intent(this, ingreso::class.java)
        startActivity(intent)
    }

    private fun registro(){
        val intent = Intent(this, registro::class.java)
        startActivity(intent)
    }
}