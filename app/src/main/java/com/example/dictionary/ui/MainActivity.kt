package com.example.dictionary.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.example.dictionary.R
import com.example.dictionary.viewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val viewModel = MainActivityViewModel()

		searchButton.setOnClickListener {
			viewModel.getWord(searchTerm.text.toString())
			val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
			imm?.hideSoftInputFromWindow(it.windowToken, 0)
		}

		viewModel.searchedWord.observe(this, Observer {
			resultWord.text = it.word
			resultType.text = it.definitions[0].type
			resultDefinition.text = it.definitions[0].definition
		})


	}
}