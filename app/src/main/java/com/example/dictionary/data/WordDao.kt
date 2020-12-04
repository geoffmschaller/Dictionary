package com.example.dictionary.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.models.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM Word")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM Word WHERE word = (:wordToFind)")
    suspend fun findWord(wordToFind: String): Word

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

}