package com.example.dictionary.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dictionary.data.WordDao
import com.example.dictionary.models.Word
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val wordDao: WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "word_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}