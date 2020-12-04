package com.example.dictionary.data.remote

import com.example.dictionary.data.responses.APIResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DictionaryAPIService {
	@Headers("Authorization: Token d3a24d837f8cdb8c0835c8df2184d861c824acb2")
	@GET("{word}?format=json")
	suspend fun getWordFromAPI(@Path(value = "word") word: String): APIResponse
}

object DictionaryAPI {
	val service: DictionaryAPIService by lazy {
		Retrofit
			.Builder()
			.baseUrl("https://owlbot.info/api/v4/dictionary/")
			.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
			.build()
			.create(DictionaryAPIService::class.java)
	}
}