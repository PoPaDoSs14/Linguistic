package com.example.linguistic.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.linguistic.domain.Level
import com.example.linguistic.domain.Words

@Dao
interface WordsDao {

    @Insert
    fun addWords(wordsDbModel: WordsDbModel)

    @Query("DELETE FROM words WHERE level=:level")
    fun deleteWords(level: String)

    @Query("SELECT * FROM words WHERE level=:level")
    fun getWord(level: String): WordsDbModel

    @Query("SELECT * FROM words")
    fun getWords(): List<WordsDbModel>
}