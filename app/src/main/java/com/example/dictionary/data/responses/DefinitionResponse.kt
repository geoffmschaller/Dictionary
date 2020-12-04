package com.example.dictionary.data.responses

data class DefinitionResponse(
    val type: String,
    val definition: String,
    val example: String,
    val image_url: String,
    val emoji: String
)
