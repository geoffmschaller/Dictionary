package com.example.dictionary.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.data.local.DictionaryDatabase
import com.example.dictionary.repositories.WordRepository
import com.example.dictionary.repositories.RepoResponse
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivityViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
			return MainActivityViewModel(context) as T
		}
		throw IllegalArgumentException("Unknown ViewModel")
	}
}

class MainActivityViewModel(context: Context) : ViewModel() {

	private val wordRepository = WordRepository()
	private val db = DictionaryDatabase.getInstance(context)
	private val coroutineJob = Job()
	private val uiScope = CoroutineScope(Dispatchers.Main + coroutineJob)
	val searchedWord = MutableLiveData<RepoResponse>()
	val errorMessage = MutableLiveData<String>()

	fun getWord(word: String) {
		uiScope.launch {
			try {
				searchedWord.value = wordRepository.getWord(db, word)
			} catch (e: Exception) {
				errorMessage.value = "I could not find that word..."
				Log.i("GEOFF", e.localizedMessage.toString())
			}
		}
	}

	override fun onCleared() {
		super.onCleared()
		coroutineJob.cancel()
	}

}