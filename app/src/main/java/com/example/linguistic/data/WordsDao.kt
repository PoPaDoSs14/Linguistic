package com.example.linguistic.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.linguistic.domain.Level
import com.example.linguistic.domain.Words

@Dao
interface WordsDao {

    @Insert
    fun addWords(wordsDbModel: WordsDbModel)

    @Query("DELETE FROM words WHERE level=:level")
    fun deleteWords(level: String)

    @Query("SELECT * FROM words WHERE level=:level LIMIT 1")
    fun getWord(level: String): WordsDbModel?

    @Query("SELECT * FROM words")
    fun getWords(): List<WordsDbModel>

    @Update
    fun updateWords(wordsDbModel: WordsDbModel)

}