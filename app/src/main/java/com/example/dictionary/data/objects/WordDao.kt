package com.example.dictionary.data.objects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.data.models.DBWord

@Dao
interface WordDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertWord(word: DBWord)

	@Query("SELECT * FROM words WHERE word = :word")
	suspend fun getSingleWord(word: String): DBWord?

}