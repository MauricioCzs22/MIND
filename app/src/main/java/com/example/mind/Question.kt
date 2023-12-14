package com.example.mind

data class Question(
    val text: String,
    val options: List<String>,
    val percentages: List<Int>
)
