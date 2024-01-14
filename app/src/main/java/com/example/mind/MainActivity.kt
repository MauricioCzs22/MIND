package com.example.mind

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btnIngresar: Button
    private lateinit var btnRegistrar: Button
    private val notificationService = NotificationService()

    // Handler para ejecutar tareas periódicas
    private val handler = Handler(Looper.getMainLooper())
    private val motivationalMessages = listOf(
        "¡Sigue adelante, tú puedes lograrlo!",
        "La perseverancia es la clave del éxito.",
        "Cada pequeño paso te acerca a grandes logros.",
        "Hoy es un buen día para alcanzar tus metas."
    )
    private var currentMessageIndex = 0

    private val notificationRunnable = object : Runnable {
        override fun run() {
            // Mostrar notificación motivacional con la frase actual
            notificationService.showNotification(
                this@MainActivity,
                "¡Motivación!",
                motivationalMessages[currentMessageIndex],
                NotificationService.NOTIFICATION_ID_MOTIVATIONAL
            )

            // Incrementar el índice para la próxima vez
            currentMessageIndex = (currentMessageIndex + 1) % motivationalMessages.size

            // Programar la siguiente notificación en 2 minutos
            handler.postDelayed(this, 2 * 60 * 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mostrar notificación al iniciar la aplicación
        notificationService.showNotification(
            this,
            "¡Bienvenido!",
            "Gracias por usar la aplicación",
            NotificationService.NOTIFICATION_ID_START
        )

        // Iniciar la tarea de notificación motivacional cada 2 minutos
        handler.postDelayed(notificationRunnable, 2 * 60 * 1000)

        // Enlace botón ingresar
        btnIngresar = findViewById(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            ingreso()
        }

        // Enlace botón registrar
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            registro()
        }
    }

    override fun onStop() {
        super.onStop()

        // Mostrar notificación al cerrar la aplicación
        notificationService.showNotification(
            this,
            "¡Hasta luego!",
            "Esperamos verte de nuevo pronto",
            NotificationService.NOTIFICATION_ID_CLOSE
        )

        // Detener la tarea de notificación motivacional al cerrar la aplicación
        handler.removeCallbacks(notificationRunnable)
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
