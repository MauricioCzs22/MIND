package com.example.mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
class Terminos : AppCompatActivity() {
    private var hideAgreementCheckbox: Boolean = false
    private lateinit var checkBox: CheckBox
    private lateinit var updatedDate: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos)

        hideAgreementCheckbox = intent.getBooleanExtra("hideAgreementCheckbox", false)

        //BOTON DE REGRESO
        val imageButton: ImageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            onBackPressed()
        }

        updatedDate = findViewById(R.id.tvFechaActualizacion)
        checkBox = findViewById(R.id.mentCheckBox)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mostrarDialogo()
            }
        }
        if(hideAgreementCheckbox){
            checkBox.visibility = View.GONE
            val params = updatedDate.layoutParams as ViewGroup.MarginLayoutParams
            val marginBottomInDp = 50
            val marginBottomInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginBottomInDp.toFloat(), resources.displayMetrics).toInt()
            params.bottomMargin = marginBottomInPx
            updatedDate.layoutParams = params
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