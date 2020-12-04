package com.example.dictionary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "pronunciation") val pronunciation: String,
    @ColumnInfo(name = "detail") val detail: String,
    //@ColumnInfo(name = "definitions") val definitions: ArrayList<DefinitionResponse>
)