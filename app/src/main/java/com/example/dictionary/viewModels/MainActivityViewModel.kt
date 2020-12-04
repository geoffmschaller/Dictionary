package com.example.dictionary.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.data.remote.DictionaryAPI
import com.example.dictionary.data.responses.APIResponse
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivityViewModel(context: Context) : ViewModel() {

	private val job = Job()
	private val uiScope = CoroutineScope(Dispatchers.Main + job)
	val searchedWord = MutableLiveData<APIResponse>()
	val errorMessage = MutableLiveData<String>()

	fun getWord(word: String) {
		uiScope.launch {
			try {
				searchedWord.value = DictionaryAPI.service.getWordFromAPI(word)
			} catch (e: Exception) {
				errorMessage.value = "I could not find that word..."
			}
		}
	}

	override fun onCleared() {
		super.onCleared()
		job.cancel()
	}

}