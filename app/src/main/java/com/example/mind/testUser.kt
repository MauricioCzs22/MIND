package com.example.mind

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mind.models.SingleQuestion
import com.example.mind.models.Test
import com.google.gson.Gson

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

    val opciones = listOf("Muy estresado", "Estresado", "Neutral", "Relajado", "Muy relajado")
    val valores = listOf(cien, setentaycinco, cincuenta, vienticinco, cero)

    val stressQuestions = listOf(
        SingleQuestion("1.- Durante el último mes, con qué frecuencia ha estado afectado por alguna situación que ocurrió inesperadamente.", opciones, valores),
        SingleQuestion("2.- Durante el último mes, con qué frecuencia se ha sentido incapaz de controlar las cosas importantes de su vida.", opciones, valores),
        SingleQuestion("3.- Durante el último mes, con qué frecuencia se ha sentido nervioso o estresado.", opciones, valores),
        SingleQuestion("4.- Durante el último mes, con qué frecuencia ha manejado con éxito los pequeños problemas irritantes de su vida.", opciones, valores),
        SingleQuestion("5.- Durante el último mes, con qué frecuencia ha sentido que ha afrontado efectivamente los cambios importantes que han estado ocurriendo en su vida.", opciones, valores),
        SingleQuestion("6.- Durante el último mes, con qué frecuencia ha estado seguro sobre su capacidad para manejar sus problemas personales.", opciones, valores),
        SingleQuestion("7.- Durante el último mes, con qué frecuencia ha sentido que las cosas le salen bien.", opciones, valores),
        SingleQuestion("8.- En el último mes, con qué frecuencia ha sentido no poder afrontar todas las cosas que debía realizar.", opciones, valores),
        SingleQuestion("9.- Durante el último mes con qué frecuencia ha podido controlar las dificultades de su vida.", opciones, valores),
        SingleQuestion("10.- Durante el último mes, con qué frecuencia ha sentido que está al control de todo.", opciones, valores),
        SingleQuestion("11.- Durante el último mes, con qué frecuencia se ha sentido molesto, porque los sucesos que le han ocurrido, estaban fuera de su control.", opciones, valores),
        SingleQuestion("12.- Durante el último mes, con qué frecuencia ha pensado sobre aquellas cosas que le quedan por lograr.", opciones, valores),
        SingleQuestion("13.- Durante el último mes, con qué frecuencia ha podido controlar su tiempo.", opciones, valores),
        SingleQuestion("14.- Durante el último mes, con qué frecuencia ha sentido que las dificultades se acumulan tanto que no puede superarlas.", opciones, valores),
    )

    val depressionQuestions = listOf(
        SingleQuestion("1.- Durante el último mes, ¿con qué frecuencia se ha sentido desanimado o triste?", opciones, valores),
        SingleQuestion("2.- Durante el último mes, ¿con qué frecuencia ha perdido interés en actividades que normalmente disfruta?", opciones, valores),
        SingleQuestion("3.- Durante el último mes, ¿con qué frecuencia ha tenido problemas para dormir o ha dormido demasiado?", opciones, valores),
        SingleQuestion("4.- Durante el último mes, ¿con qué frecuencia ha experimentado cambios en su apetito o peso?", opciones, valores),
        SingleQuestion("5.- Durante el último mes, ¿con qué frecuencia se ha sentido cansado o con falta de energía?", opciones, valores),
        SingleQuestion("6.- Durante el último mes, ¿con qué frecuencia ha tenido dificultades para concentrarse en tareas cotidianas?", opciones, valores),
        SingleQuestion("7.- Durante el último mes, ¿con qué frecuencia se ha sentido inútil o culpable por cosas que han ocurrido?", opciones, valores),
        SingleQuestion("8.- Durante el último mes, ¿con qué frecuencia ha tenido pensamientos negativos sobre usted mismo o su futuro?", opciones, valores),
        SingleQuestion("9.- Durante el último mes, ¿con qué frecuencia se ha sentido irritable o fácilmente molesto?", opciones, valores),
        SingleQuestion("10.- Durante el último mes, ¿con qué frecuencia ha pensado en hacerse daño o que sería mejor si no estuviera?", opciones, valores),
        SingleQuestion("11.- Durante el último mes, ¿con qué frecuencia ha tenido dificultades para realizar actividades diarias debido a su estado de ánimo?", opciones, valores),
        SingleQuestion("12.- Durante el último mes, ¿con qué frecuencia se ha sentido aislado o distante de otras personas?", opciones, valores),
        SingleQuestion("13.- Durante el último mes, ¿con qué frecuencia ha sentido que nada puede animarlo?", opciones, valores),
        SingleQuestion("14.- Durante el último mes, ¿con qué frecuencia ha sentido que su vida no tiene dirección o propósito?", opciones, valores)
    )

    val anxietyQuestions = listOf(
        SingleQuestion("1.- Durante el último mes, ¿con qué frecuencia se ha sentido nervioso, ansioso o tenso?", opciones, valores),
        SingleQuestion("2.- Durante el último mes, ¿con qué frecuencia no ha podido dejar de preocuparse por cosas que son poco probables que sucedan?", opciones, valores),
        SingleQuestion("3.- Durante el último mes, ¿con qué frecuencia ha tenido ataques de pánico o miedo repentino?", opciones, valores),
        SingleQuestion("4.- Durante el último mes, ¿con qué frecuencia se ha preocupado excesivamente por diferentes cosas?", opciones, valores),
        SingleQuestion("5.- Durante el último mes, ¿con qué frecuencia ha tenido dificultad para relajarse?", opciones, valores),
        SingleQuestion("6.- Durante el último mes, ¿con qué frecuencia ha estado tan inquieto que le ha sido difícil quedarse quieto?", opciones, valores),
        SingleQuestion("7.- Durante el último mes, ¿con qué frecuencia se ha irritado o ha tenido arrebatos de enojo?", opciones, valores),
        SingleQuestion("8.- Durante el último mes, ¿con qué frecuencia se ha sentido temeroso sin una razón clara?", opciones, valores),
        SingleQuestion("9.- Durante el último mes, ¿con qué frecuencia ha tenido problemas para conciliar el sueño o permanecer dormido debido a la preocupación?", opciones, valores),
        SingleQuestion("10.- Durante el último mes, ¿con qué frecuencia ha sentido miedo de que ocurra algo terrible?", opciones, valores),
        SingleQuestion("11.- Durante el último mes, ¿con qué frecuencia ha sentido mareos, dificultad para respirar o palpitaciones sin un esfuerzo físico claro?", opciones, valores),
        SingleQuestion("12.- Durante el último mes, ¿con qué frecuencia se ha sentido incapaz de controlar sus preocupaciones?", opciones, valores),
        SingleQuestion("13.- Durante el último mes, ¿con qué frecuencia ha evitado situaciones que le causan ansiedad?", opciones, valores),
        SingleQuestion("14.- Durante el último mes, ¿con qué frecuencia ha tenido la sensación de que algo malo va a pasar?", opciones, valores)
    )

    val stressTest = Test("Test de Estrés", stressQuestions)
    val depressionTest = Test("Test de Depresión", depressionQuestions)
    val anxietyTest = Test("Test de Ansiedad", anxietyQuestions)

    private var testScores = mutableMapOf<String, Int>()
    private var testAverages = mutableListOf<Triple<String, Int, String>>()

    private var currentTestIndex = 0
    private var currentQuestionIndex = 0
    private var totalPercentage = 0
    private val allTests = listOf(stressTest, depressionTest, anxietyTest)


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

        showTest(allTests[currentTestIndex])
    }

    private fun showTest(test: Test) {
        label_question.text = test.testName
        showQuestion(test.questions[currentQuestionIndex])
    }

    private fun showQuestion(question: SingleQuestion) {
        text_question.text = question.questionText

        rdgroup.clearCheck()

        for (i in question.options.indices) {
            val radioButton = findViewById<RadioButton>(resources.getIdentifier("radio_button_${i + 1}", "id", packageName))
            radioButton.text = question.options[i]
        }
    }

    private fun showNextQuestion() {
        val selectedOption = getSelectedOption()

        if (selectedOption != -1) {
            val currentTest = allTests[currentTestIndex]
            val currentScore = testScores.getOrElse(currentTest.testName) { 0 }
            testScores[currentTest.testName] = currentScore + currentTest.questions[currentQuestionIndex].values[selectedOption]

            totalPercentage += allTests[currentTestIndex].questions[currentQuestionIndex].values[selectedOption]
            currentQuestionIndex++

            if (currentQuestionIndex < allTests[currentTestIndex].questions.size) {
                showQuestion(allTests[currentTestIndex].questions[currentQuestionIndex])
            } else {
                currentTestIndex++
                if (currentTestIndex < allTests.size) {
                    currentQuestionIndex = 0
                    showTest(allTests[currentTestIndex])
                } else {
                    showFinalResult()
                }
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
        for ((testName, score) in testScores) {
            val average = score / allTests.first { it.testName == testName }.questions.size
            val resultMessage = getResultMessage(average)
            testAverages.add(Triple(testName, average, resultMessage))
        }

        goToNextActivity()
    }

    private fun goToNextActivity() {
        val gson = Gson()
        val testAveragesJson = gson.toJson(testAverages)

        val intent = Intent(this, GraphsActivity::class.java)
        intent.putExtra("testAverages", testAveragesJson)
        startActivity(intent)
    }

    private fun getResultMessage(percentageRange: Int): String {
        return when (percentageRange) {
            in 0..25 -> "Estás bien de salud"
            in 26..50 -> "Bien, pero podrías considerar gestionar el estrés"
            in 51..75 -> "Moderadamente estresado, es recomendable visitar a algun profesional"
            in 76..100 -> "Estresado, considera una cita con el psicólogo"
            else -> "Necesitas hacer una cita con alguno de nuestros psicologos"
        }
    }

//    private fun showfinalresult() {
//        val averagepercentage = totalpercentage / alltests.sumof { it.questions.size }
//        log.d("averagepercentage", averagepercentage.tostring())
//        // dividir el resultado total en cinco partes (0-56, 57-112, 113-168, 169-224, 225-280)
//        val percentagerange = 0
//
//        // mostrar mensaje de acuerdo a la puntuación obtenida
//        val resultmessage = when (percentagerange) {
//            in 0..25 -> "estás bien de salud"
//            in 26..50 -> "bien, pero podrías considerar gestionar el estrés"
//            in 51..75 -> "moderadamente estresado, es recomendable visitar a algun profesional"
//            in 76..100 -> "estresado, considera una cita con el psicólogo"
//            else -> "necesitas hacer una cita con alguno de nuestros psicologos"
//        }// abrir la clase terminos
//        val intent = intent(this, principalview::class.java)
//        intent.putextra("resultmessage", resultmessage)
//        startactivity(intent)
//        handler().postdelayed({
//            startactivity(intent)
//        }, 3000)
//
//
//        // crear un alertdialog
//        val alertdialogbuilder = alertdialog.builder(this)
//        alertdialogbuilder
//            .settitle("resultado del test de estrés")
//            .setmessage("$resultmessage\n\nresultado total: $percentagerange%")
//            .setpositivebutton("aceptar") { _, _ ->
//                // acciones adicionales al hacer clic en aceptar
//                // puedes redirigir al usuario a otra pantalla, cerrar la actividad, etc.
//            }
//            .setcancelable(false)  // no permitir que el usuario cierre el diálogo al tocar fuera de él
//
//        // mostrar el alertdialog
//        val alertdialog = alertdialogbuilder.create()
//        alertdialog.show()
//    }

}