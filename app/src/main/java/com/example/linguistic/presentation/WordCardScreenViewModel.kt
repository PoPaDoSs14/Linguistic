package com.example.linguistic.presentation

import android.app.Application
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
            if (repo.getWords() == null){
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

    fun getWords(level: String): Words?{
        var words: Words? = null
        viewModelScope.launch(Dispatchers.IO) {
            words = repo.getWord(level)
        }
        return words
    }


}