package com.example.mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ayuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)

        val btnRetro: ImageButton = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener {
            onBackPressed()
        }
    }
}