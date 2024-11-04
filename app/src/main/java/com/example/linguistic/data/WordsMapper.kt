package com.example.linguistic.data

import com.example.linguistic.domain.User
import com.example.linguistic.domain.Words

class WordsMapper {

    val converters = Converters()

    fun mapEntityToDbModel(words: Words): WordsDbModel{
        return WordsDbModel(
            id = words.id,
            level = converters.fromLevel(words.level),
            words = converters.fromWordsList(words.words)
        )
    }

    fun mapDbModelToEntity(wordsDbModel: WordsDbModel): Words? {
        return Words(
            id = wordsDbModel.id,
            level = converters.toLevel(wordsDbModel.level),
            words = converters.toWordsList(wordsDbModel.words)
        )
    }


    fun mapListDbModelToListEntity(list: List<WordsDbModel>): List<Words> = list.map {
        mapDbModelToEntity(it)!!
    }
}