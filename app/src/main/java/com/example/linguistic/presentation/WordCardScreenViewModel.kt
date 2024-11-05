package com.example.linguistic.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguistic.data.RepositoryImpl
import com.example.linguistic.domain.Level
import com.example.linguistic.domain.User
import com.example.linguistic.domain.Words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordCardScreenViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)

    private var _easyWords = mutableStateOf<Words?>(null)
    val easyWords: State<Words?> = _easyWords

    private var _normalWords = mutableStateOf<Words?>(null)
    val normalWords: State<Words?> = _normalWords

    private var _hardWords = mutableStateOf<Words?>(null)
    val hardWords: State<Words?> = _hardWords


    private var isUpdatingCount = false

    fun addKnowWord() {
        if (isUpdatingCount) return

        isUpdatingCount = true

        viewModelScope.launch(Dispatchers.IO) {
            val user = repo.getUser(1)
            if (user != null) {
                val updatedCount = user.countOfWord + 1
                user.countOfWord = updatedCount
                update(user)
            }
            isUpdatingCount = false
        }
    }

    suspend fun update(user: User) {
        repo.update(user)

    }



    fun loadingWords(){
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.getWords().isEmpty()){
                repo.addWords(Words(0, Level.EASY, listOf(
                    Pair("Cat", "Кот"),
                    Pair("Dog", "Собака"),
                    Pair("Sun", "Солнце"),
                    Pair("Moon", "Луна"),
                    Pair("Tree", "Дерево"),
                    Pair("Water", "Вода"),
                    Pair("House", "Дом"),
                    Pair("Book", "Книга"),
                    Pair("Chair", "Стул"),
                    Pair("Table", "Стол"))))



                repo.addWords(Words(0, Level.MEDIUM, listOf(
                    Pair("School", "Школа"),
                    Pair("Friend", "Друг"),
                    Pair("Family", "Семья"),
                    Pair("Happy", "Счастливый"),
                    Pair("Music", "Музыка"),
                    Pair("Travel", "Путешествие"),
                    Pair("City", "Город"),
                    Pair("Animal", "Животное"),
                    Pair("Food", "Еда"),
                    Pair("Market", "Рынок")
                )))

                repo.addWords(Words(0, Level.HARD, listOf(
                    Pair("Philosophy", "Философия"),
                    Pair("Antidisestablishmentarianism", "Антидизестаблишментаризм"),
                    Pair("Constitutional", "Конституционный"),
                    Pair("Metamorphosis", "Метаморфоза"),
                    Pair("Pneumonoultramicroscopicsilicovolcanoconiosis", "Пневмокониоз"),
                    Pair("Cryptocurrency", "Криптовалюта"),
                    Pair("Psychotherapy", "Психотерапия"),
                    Pair("Electromagnetism", "Электромагнетизм"),
                    Pair("Bureaucracy", "Бюрократия"),
                    Pair("Incomprehensible", "Непонятный")
                )))
            }
        }

    }

    fun getWords(level: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val words = repo.getWord(level)
            when (level) {
                "EASY" -> _easyWords.value = words
                "MEDIUM" -> _normalWords.value = words
                "HARD" -> _hardWords.value = words
            }
        }
    }

    fun updateWords(words: Words){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateWords(words)
        }
    }

    fun deleteWord(word: String, level: Level) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedWords = when (level) {
                Level.EASY -> {
                    val currentEasyWords = _easyWords.value?.words ?: emptyList()
                    currentEasyWords.filter { it.first != word }
                }
                Level.MEDIUM -> {
                    val currentNormalWords = _normalWords.value?.words ?: emptyList()
                    currentNormalWords.filter { it.first != word }
                }
                Level.HARD -> {
                    val currentHardWords = _hardWords.value?.words ?: emptyList()
                    currentHardWords.filter { it.first != word }
                }
            }


            when (level) {
                Level.EASY -> _easyWords.value = _easyWords.value?.copy(words = updatedWords)
                Level.MEDIUM -> _normalWords.value = _normalWords.value?.copy(words = updatedWords)
                Level.HARD -> _hardWords.value = _hardWords.value?.copy(words = updatedWords)
            }

            repo.deleteWords(level.name)

            repo.addWords(Words(0, level, updatedWords))
        }
    }


}