package com.example.dictionary.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.viewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	lateinit var viewModel: MainActivityViewModel
	lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		searchButton.setOnClickListener {
			if (searchTerm.text.toString().isEmpty()) {
				Toast.makeText(this, "A Word is required", Toast.LENGTH_SHORT).show()
			} else {
				viewModel.getWord(searchTerm.text.toString())
				val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
				imm?.hideSoftInputFromWindow(it.windowToken, 0)
				searchTerm.text.clear()
			}
		}

		viewModel.searchedWord.observe(this, Observer {
			resultWord.text = it.word
			resultType.text = it.definitions[0].type
			resultDefinition.text = it.definitions[0].definition
		})

		viewModel.errorMessage.observe(this, Observer {
			resultDefinition.text = it
			resultWord.text = ""
			resultType.text = ""
		})


	}
}