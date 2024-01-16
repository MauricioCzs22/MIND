package com.example.mind.chatbot2.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepalace.chatbot.utils.BotResponse
import com.example.mind.R
import com.example.mind.chatbot2.data.Message
import com.example.mind.chatbot2.utils.Constants.OPEN_GOOGLE
import com.example.mind.chatbot2.utils.Constants.OPEN_SEARCH
import com.example.mind.chatbot2.utils.Constants.RECEIVE_ID
import com.example.mind.chatbot2.utils.Constants.SEND_ID
import com.example.mind.chatbot2.utils.Time
import com.google.ai.client.generativeai.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Chat : AppCompatActivity() {
    private val TAG = "Chat"

    var messagesList = mutableListOf<Message>()
    private lateinit var btn_send: Button
    private lateinit var adapter: MessagingAdapter
    private lateinit var rv_messages: RecyclerView
    private lateinit var et_message: EditText
    private val botList = listOf(" MindAI", " MindAI", " MindAI", " MindAI")

    //private val isBienvenidaChat: false
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        rv_messages = findViewById(R.id.rv_messages)
        et_message = findViewById(R.id.et_message)
        btn_send = findViewById(R.id.btn_send)
        recyclerView()
        clickEvents()
        val random = (0..3).random()
        customMessage("Hola, hoy hablaremos con ${botList[random]},necesitas ayuda?")

        val btnRetro: ImageButton = findViewById(R.id.btnRetro)
        btnRetro.setOnClickListener {
            onBackPressed()
        }
    }


    private fun clickEvents() {
        btn_send.setOnClickListener {
            sendMessage()
        }

        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            messagesList.add(Message(message, SEND_ID, timeStamp))

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)
            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)
            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }

                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }

    private fun customMessage(message: String) {

        GlobalScope.launch {
            delay(timeMillis = 1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}