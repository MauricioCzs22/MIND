package com.example.mind

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class testUser : AppCompatActivity() {
    private lateinit var btnNext: Button
    private lateinit var text_question: TextView
    private lateinit var label_question: TextView
    private lateinit var rdgroup: RadioGroup

    val cien: Int = 100
    val setentaycinco: Int = 75
    val cincuenta: Int = 50
    val vienticinco: Int = 25
    val cero: Int = 0


    private val questions = listOf(
        Question("1.- Durante el último mes, con qué frecuencia ha estado afectado por alguna situación que ocurrió inesperadamente.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        // Agregar más preguntas según sea necesario
        Question("2.- Durante el último mes, con qué frecuencia se ha sentido incapaz de controlar las cosas importantes de su vida.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("3.- Durante el último mes, con qué frecuencia se ha sentido nervioso o estresado.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("4.- Durante el último mes, con qué frecuencia ha manejado con éxito los pequeños problemas irritantes de su vida.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("5.- Durante el último mes, con qué frecuencia ha sentido que ha afrontado efectivamente los cambios importantes que han estado ocurriendo en su vida.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("6.- Durante el último mes, con qué frecuencia ha estado seguro sobre su capacidad para manejar sus problemas personales.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("7.- Durante el último mes, con qué frecuencia ha sentido que las cosas le salen bien.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("8.- En el último mes, con qué frecuencia ha sentido no poder afrontar todas las cosas que debía realizar.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("9.- Durante el último mes con qué frecuencia ha podido controlar las dificultades de su vida.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("10.- Durante el último mes, con qué frecuencia ha sentido que está al control de todo.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("11.- Durante el último mes, con qué frecuencia se ha sentido molesto, porque los sucesos que le han ocurrido, estaban fuera de su control.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("12.- Durante el último mes, con qué frecuencia ha pensado sobre aquellas cosas que le quedan por lograr.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("13.- Durante el último mes, con qué frecuencia ha podido controlar su tiempo.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),

        Question("14.- Durante el último mes, con qué frecuencia ha sentido que las dificultades se acumulan tanto que no puede superarlas.",
            listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado"),
            listOf(cien, setentaycinco, cincuenta, vienticinco, cero)),
    )

    private var currentQuestionIndex = 0
    private var totalPercentage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_user)

        btnNext = findViewById(R.id.btnNext)
        text_question = findViewById(R.id.text_question)
        label_question = findViewById(R.id.label_question)
        rdgroup = findViewById(R.id.rdgroup)


        btnNext.setOnClickListener {
            showNextQuestion()
        }

        showQuestion(questions[currentQuestionIndex])
    }

    private fun showQuestion(question: Question) {
        label_question.text = "Test de estrés"
        text_question.text = question.text

        rdgroup.clearCheck()

        for (i in question.options.indices) {
            val radioButton = findViewById<RadioButton>(resources.getIdentifier("radio_button_${i + 1}", "id", packageName))
            radioButton.text = question.options[i]
        }
    }

    private fun showNextQuestion() {
        val selectedOption = getSelectedOption()

        if (selectedOption != -1) {
            totalPercentage += questions[currentQuestionIndex].percentages[selectedOption]
            Toast.makeText(this, "$totalPercentage", Toast.LENGTH_SHORT).show()
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                showQuestion(questions[currentQuestionIndex])
            } else {
                showFinalResult()
            }
        } else {
            Toast.makeText(this, "Por favor, selecciona una respuesta", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSelectedOption(): Int {
        val selectedRadioButtonId = rdgroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            return rdgroup.indexOfChild(selectedRadioButton)
        }

        return -1
    }

    private fun showFinalResult() {
        // Dividir el resultado total en cinco partes (0-56, 57-112, 113-168, 169-224, 225-280)
        val percentageRange = totalPercentage / questions.size

        // Mostrar mensaje de acuerdo a la puntuación obtenida
        val resultMessage = when (percentageRange) {
            in 0..25 -> "Estás bien de salud"
            in 26..50 -> "Bien, pero podrías considerar gestionar el estrés"
            in 51..75 -> "Moderadamente estresado, es recomendable hablar con alguien"
            in 76..100 -> "Estresado, considera una cita con el psicólogo"
            else -> "Necesitas hacer una cita con el área de psicología"
        }

        // Crear un AlertDialog
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder
            .setTitle("Resultado del test de estrés")
            .setMessage("$resultMessage\n\nResultado total: $percentageRange%")
            .setPositiveButton("Aceptar") { _, _ ->
                // Acciones adicionales al hacer clic en Aceptar
                // Puedes redirigir al usuario a otra pantalla, cerrar la actividad, etc.
            }
            .setCancelable(false)  // No permitir que el usuario cierre el diálogo al tocar fuera de él

        // Mostrar el AlertDialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}