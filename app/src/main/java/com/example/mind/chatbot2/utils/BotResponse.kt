package com.codepalace.chatbot.utils

import android.annotation.SuppressLint
import com.example.mind.chatbot2.utils.Constants.OPEN_GOOGLE
import com.example.mind.chatbot2.utils.Constants.OPEN_SEARCH
import com.example.mind.chatbot2.utils.SolveMath
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    @SuppressLint("SuspiciousIndentation")
    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

            return when {

                //Flips a coin
                message.contains("flip") && message.contains("coin") -> {
                    val r = (0..1).random()
                    val result = if (r == 0) "heads" else "tails"

                    "I flipped a coin and it landed on $result"
                }

                //Math calculations
                message.contains("solve") -> {
                    val equation: String? = message.substringAfterLast("solve")
                    return try {
                        val answer = SolveMath.solveMath(equation ?: "0")
                        "$answer"

                    } catch (e: Exception) {
                        "Sorry, I can't solve that."
                    }
                }

                //Hello
                message.contains("hola") -> {
                    when (random) {
                        0 -> "Hola, ¿Cómo estas?"
                        1 -> "Que tal, buen día!"
                        2 -> "Gusto en saludarte, ¿puedo ayudarte en algo?"
                        else -> "error"
                    }
                }

                //How are you?
                message.contains("como estas?") -> {
                    when (random) {
                        0 -> "Yo estoy muy bien, gracias. ¿Tú como estas?"
                        1 -> "Soy una IA, no tengo sentimientos, pero bien gracias ¿Y tú?"
                        2 -> "Muy bien! ¿Qué hay de ti?"
                        else -> "error"
                    }
                }
                //bien?
                message.contains("bien") -> {
                    when (random) {
                        0 -> "Me alegra saber que estás bien"
                        1 -> "¿Qué te hace sentir bien hoy?"
                        2 -> "¿Qué cosas te hacen feliz?"
                        else -> "error"
                    }
                }
                //Si
                message.contains("si") -> {
                    when (random) {
                        0 -> "¿Cómo te sientes?"
                        1 -> "¿Cómo te has sentido los ultimos dias!"
                        2 -> "¿Quieres hablar al respecto?"
                        else -> "error"
                    }
                }
                //Estresado
                message.contains("estres") -> {
                    when (random) {
                        0 -> "Siento mucho, escuchar que estás estresado. ¿Hay algo que pueda hacer para ayudarte?"
                        1 -> "¿Qué te está estresando?"
                        2 -> "¿Te gustaría hablar de ello?"
                        else -> "error"
                    }
                }
                //nervioso
                message.contains("nervioso") -> {
                    when (random) {
                        0 -> "Siento mucho, escuchar que estás nervioso. ¿Hay algo que pueda hacer para ayudarte?"
                        1 -> "¿Qué te está poniendo nervioso?"
                        2 -> "Aquí hay algunas cosas que puedes hacer para aliviar el nerviosismo: hacer ejercicio, escuchar música relajante, pasar tiempo con amigos y familiares, o tomar un descanso"
                        else -> "error"
                    }
                }
                //nervioso
                message.contains("frustrado") -> {
                    when (random) {
                        0 -> "Lo siento mucho escuchar que estás frustrado. ¿Hay algo que pueda hacer para ayudarte?"
                        1 -> "¿Qué te está frustrando?"
                        2 -> "Aquí hay algunas cosas que puedes hacer para aliviar la frustración: hacer ejercicio, escuchar música relajante, pasar tiempo con amigos y familiares, o tomar un descanso"
                        else -> "error"
                    }
                }
                //cambios
                message.contains("cambios") -> {
                    when (random) {
                        0 -> "Me alegra que hayas notado los cambios. Es importante estar consciente de cómo te sientes y buscar ayuda si es necesario."
                        1 -> "¿Podrías contarme más sobre los cambios que has notado?"
                        2 -> "¿Crees que los cambios pueden estar relacionados con algo específico?"
                        else -> "error"
                    }
                }
                message.contains("triste") -> {
                    when (random) {
                        0 -> "Lo siento mucho, escuchar que estás triste. ¿Hay algo que pueda hacer para ayudarte?"
                        1 -> "¿Qué te está causando tristeza?"
                        2 -> "Aquí hay algunas cosas que puedes hacer para sentirte mejor: hacer ejercicio, escuchar música relajante, pasar tiempo con amigos y familiares, o tomar un descanso"
                        else -> "error"
                    }
                }

                //Busca
                message.contains("busca") -> {
                    when (random) {
                        0 -> "Lo siento mucho, no puedo ayudarte con eso"
                        1 -> "Lo que me solicitas se encuentra fuera de mis funciones"
                        2 -> "Lo siento mucho, mis funciones me delimitan"

                        else -> "error"
                    }
                }
                //AYUDA
                message.contains("ayuda") -> {
                    when (random) {
                        0 -> "Entiendo, a continuación te brindare el contacto de psicologos que podrían brindarte una mejor atención psicologíca"
                        1 -> "En el apartado de ayuda, podras encontrar el contacto de distintos psicologos que pueden brindarte ayuda especializada"
                        2 -> "¿Ayuda especializada?"
                        else -> "error"
                    }
                }

                //What time is it?
                message.contains("time") && message.contains("?") -> {
                    val timeStamp = Timestamp(System.currentTimeMillis())
                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                    val date = sdf.format(Date(timeStamp.time))

                    date.toString()
                }

                //Open Google
                message.contains("open") && message.contains("google") -> {
                    OPEN_GOOGLE
                }

                //Search on the internet
                message.contains("search") -> {
                    OPEN_SEARCH
                }

                //When the programme doesn't understand...
                else -> {
                    when (random) {
                        0 -> "Lo siento, no logro entenderte"
                        1 -> "Podrias preguntarme algo diferente"
                        2 -> "No lo sé"
                        else -> "error"
                    }
                }
            }
    }
}