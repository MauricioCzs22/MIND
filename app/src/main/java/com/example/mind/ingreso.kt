package com.example.mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class ingreso : AppCompatActivity() {

    private lateinit var txRegis: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        //boton retorno
        val btnRetro: ImageView = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener{
            onBackPressed()
        }
        //boton cancelar pruea
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