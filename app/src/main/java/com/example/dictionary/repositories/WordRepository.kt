package com.example.dictionary.repositories

import android.util.Log
import com.example.dictionary.data.local.DictionaryDatabase
import com.example.dictionary.data.models.DBWord
import com.example.dictionary.data.remote.DictionaryAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class RepoResponse(
	val word: String,
	val definition: String,
	val part: String
)

class WordRepository {

	suspend fun getWord(db: DictionaryDatabase, word: String): RepoResponse {
		var response: RepoResponse
		withContext(Dispatchers.IO) {
			when (val dbResult = db.wordDao.getSingleWord(word)) {
				null -> {
					Log.i("GEOFF", "Not in DB. Searching API...")
					val apiResult = DictionaryAPI.service.getWordFromAPI(word)
					db.wordDao.insertWord(
						DBWord(
							uid = Random.nextInt(0, 99999),
							word = apiResult.word,
							definition = apiResult.definitions[0].definition,
							part = apiResult.definitions[0].type
						)
					)
					response = RepoResponse(
						word = apiResult.word,
						definition = apiResult.definitions[0].definition,
						part = apiResult.definitions[0].type
					)
				}
				else -> {
					Log.i("GEOFF", "Found in DB!")
					response = RepoResponse(
						word = dbResult.word,
						definition = dbResult.definition,
						part = dbResult.part
					)
				}
			}
		}
		return response
	}

}