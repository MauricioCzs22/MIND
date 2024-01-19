package com.example.mind

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.annotation.Nonnull

class cuenta : AppCompatActivity() {

    private lateinit var mTextViewData: TextView
    private lateinit var mTextViewData2: TextView
    private lateinit var mTextViewData3: TextView
    private lateinit var mDataBase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)

        mTextViewData = findViewById(R.id.textViewData)
        mTextViewData2 = findViewById(R.id.textViewData2)
        mTextViewData3 = findViewById(R.id.textViewData3)

        mDataBase = FirebaseDatabase.getInstance().getReference()

        val btnRetro: ImageButton = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener {
            onBackPressed()
        }

        // Obtenemos el UID del usuario actual
        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        mDataBase.child("Users").child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Obtenemos el nombre y telefono del usuario
                    val nombre = dataSnapshot.child("f_nombre").getValue(String::class.java)
                    val telefono = dataSnapshot.child("f_telefono").getValue(String::class.java)
                    val correo = dataSnapshot.child("f_emai").getValue(String::class.java)

                    mTextViewData.setText("$nombre")
                    mTextViewData2.setText("$telefono")
                    mTextViewData3.setText("$correo")
                } else {
                    mTextViewData.setText("No se encontró el nombre")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar el error
            }
        })

    }
}
