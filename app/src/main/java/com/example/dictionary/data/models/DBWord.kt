package com.example.dictionary.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
class DBWord(
	@PrimaryKey val uid: Int,
	@ColumnInfo(name = "word") val word: String,
	@ColumnInfo(name = "definition") val definition: String,
	@ColumnInfo(name = "part") val part: String
)