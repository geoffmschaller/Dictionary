package com.example.dictionary.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.data.WordRepository
import com.example.dictionary.data.responses.DictionaryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

	private val repo = WordRepository()
	private val job = Job()
	private val uiScope = CoroutineScope(Dispatchers.Main + job)
	val searchedWord = MutableLiveData<DictionaryResponse>()

	fun getWord(word: String) {
		uiScope.launch {
			searchedWord.value = repo.getWord(word)
		}
	}

	override fun onCleared() {
		super.onCleared()
		job.cancel()
	}

}