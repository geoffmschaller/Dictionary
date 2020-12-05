package com.example.dictionary.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dictionary.data.models.DBWord
import com.example.dictionary.data.objects.WordDao

@Database(entities = [DBWord::class], version = 2, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {
	abstract val wordDao: WordDao

	companion object {
		@Volatile
		private var INSTANCE: DictionaryDatabase? = null

		fun getInstance(context: Context): DictionaryDatabase {
			synchronized(this) {
				var instance = INSTANCE
				if (instance == null) {
					instance = Room.databaseBuilder(
						context.applicationContext,
						DictionaryDatabase::class.java,
						"dictionary_database"
					).fallbackToDestructiveMigration().build()
				}
				return instance
			}
		}

	}
}