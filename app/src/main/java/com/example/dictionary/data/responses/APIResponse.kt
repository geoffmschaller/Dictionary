package com.example.dictionary.data.responses

data class APIResponse(
	val word: String,
	val pronunciation: String?,
	val detail: String,
	val definitions: ArrayList<APIDefinitionResponse>

)

data class APIDefinitionResponse(
	val type: String,
	val definition: String,
	val example: String,
	val image_url: String,
	val emoji: String
)