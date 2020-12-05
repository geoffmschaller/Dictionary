package com.example.dictionary.repositories

import com.example.dictionary.data.remote.DictionaryAPI
import com.example.dictionary.data.responses.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository {

	suspend fun getWord(word: String): APIResponse {
		var response: APIResponse
		withContext(Dispatchers.IO) {
			response = DictionaryAPI.service.getWordFromAPI(word)
		}
		return response
	}

}