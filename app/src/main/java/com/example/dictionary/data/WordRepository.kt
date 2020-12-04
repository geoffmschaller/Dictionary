package com.example.dictionary.data

import com.example.dictionary.data.remote.DictionaryAPI

class WordRepository {
	suspend fun getWord(word: String) = DictionaryAPI.service.getWordFromAPI(word)
}