package com.example.dictionary.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.repositories.WordRepository
import com.example.dictionary.data.responses.APIResponse
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivityViewModel : ViewModel() {

	private val wordRepository = WordRepository()
	private val coroutineJob = Job()
	private val uiScope = CoroutineScope(Dispatchers.Main + coroutineJob)
	val searchedWord = MutableLiveData<APIResponse>()
	val errorMessage = MutableLiveData<String>()

	fun getWord(word: String) {
		uiScope.launch {
			try {
				searchedWord.value = wordRepository.getWord(word)
			} catch (e: Exception) {
				errorMessage.value = "I could not find that word..."
			}
		}
	}

	override fun onCleared() {
		super.onCleared()
		coroutineJob.cancel()
	}

}