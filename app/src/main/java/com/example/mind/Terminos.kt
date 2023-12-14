package com.example.mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView

class Terminos : AppCompatActivity() {

    private lateinit var mentCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos)

        //Boton retroceso
        /*val mentCheckBox: CheckBox = findViewById(R.id.mentCheckBox)
        mentCheckBox.setOnClickListener{
            onBackPressed()
        }*/
    }
    /*private fun Cuenta(){
        val intent = Intent(this, Terminos::class.java)
        startActivity(intent)
    }*/
}