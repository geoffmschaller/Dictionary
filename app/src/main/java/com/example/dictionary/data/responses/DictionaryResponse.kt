package com.example.dictionary.data.responses

data class DictionaryResponse(
    val word: String,
    val pronunciation: String,
    val detail: String,
    val definitions: ArrayList<DefinitionResponse>
)
