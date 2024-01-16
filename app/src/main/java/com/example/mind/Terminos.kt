package com.example.mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
class Terminos : AppCompatActivity() {

    private lateinit var checkBox: CheckBox

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos)

        //BOTON DE REGRESO
        val imageButton: ImageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            onBackPressed()
        }

        checkBox = findViewById(R.id.mentCheckBox)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mostrarDialogo()
            }
        }
    }

    //FUNCION DEL ALERT
    private fun mostrarDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("CONFIRMAR PARA CONTINUAR")
            .setMessage("Has revisado los términos y condiciones. ¿Estás seguro de continuar?")
            .setPositiveButton("Aceptar") { dialog, which ->
                abrirMainActivity()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss() // Cierra el diálogo
            }
            .show()
    }

    private fun abrirMainActivity() {
        val intent = Intent(this, testUser::class.java)
        startActivity(intent)
        finish() // Para cerrar la actividad actual si es necesario
    }

}