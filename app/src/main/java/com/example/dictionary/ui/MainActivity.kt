package com.example.dictionary.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.viewModels.MainActivityViewModel
import com.example.dictionary.viewModels.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	lateinit var viewModel: MainActivityViewModel
	lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel = ViewModelProvider(
			this,
			MainActivityViewModelFactory(this)
		).get(MainActivityViewModel::class.java)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		searchButton.setOnClickListener {
			when (searchTerm.text.toString().isEmpty()) {
				true -> Toast.makeText(this, "A Word is required", Toast.LENGTH_SHORT).show()
				else -> {
					viewModel.getWord(searchTerm.text.toString())
					searchTerm.text.clear()
					searchTerm.clearFocus()
					hideKeyboard(it)
				}
			}
		}

		viewModel.searchedWord.observe(this, Observer {
			resultWord.text = it.word
			resultType.text = it.part
			resultDefinition.text = it.definition
		})

		viewModel.errorMessage.observe(this, Observer {
			resultDefinition.text = it
			resultWord.text = ""
			resultType.text = ""
		})

	}

	private fun hideKeyboard(view: View) {
		val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
		imm?.hideSoftInputFromWindow(view.windowToken, 0)
	}
}