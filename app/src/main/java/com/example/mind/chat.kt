package com.example.mind

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepalace.chatbot.utils.BotResponse
import com.example.mind.chatbot2.data.Message
import com.example.mind.chatbot2.ui.MessagingAdapter
import com.example.mind.chatbot2.utils.Constants.OPEN_GOOGLE
import com.example.mind.chatbot2.utils.Constants.OPEN_SEARCH
import com.example.mind.chatbot2.utils.Constants.RECEIVE_ID
import com.example.mind.chatbot2.utils.Constants.SEND_ID
import com.example.mind.chatbot2.utils.Time
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class chat : AppCompatActivity() {
    private val TAG = "chat"
    var messagesList = mutableListOf<Message>()
    private lateinit var btn_send: Button
    private lateinit var adapter: MessagingAdapter
    private lateinit var rv_messages: RecyclerView
    private lateinit var et_message: RecyclerView
    private val botList = listOf("Peter","Francesa","Luigi","Igor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv_messages = findViewById(R.id.rv_messages)
        et_message = findViewById(R.id.et_message)
        setContentView(R.layout.activity_chat)
        recyclerView()
        clickEvents()
        val random = (0..3).random()
        customMessage("Hola, hoy hablaremos con ${botList[random]},necesitas ayuda?")
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
        val message = et_message.toString()
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
    private fun customMessage(message: String){
        GlobalScope.launch{
            delay(timeMillis = 1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID,timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)

            }
        }
    }

}