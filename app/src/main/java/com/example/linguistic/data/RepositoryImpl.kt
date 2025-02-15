package com.example.linguistic.data

import android.app.Application
import androidx.navigation.NavHostController
import com.example.linguistic.domain.Level
import com.example.linguistic.domain.Repository
import com.example.linguistic.domain.User
import com.example.linguistic.domain.Words
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val application: Application): Repository {

    private val mapper = UserMapper()
    private val wordsMapper = WordsMapper()
    private val dao = AppDatabase.getInstance(application).userDao()
    private val wordsDao = AppDatabase.getInstance(application).wordsDao()

    override suspend fun addUser(user: User) {
        dao.addUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun getUsers(): Flow<List<User>> = dao.getUsers().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getUser(id: Int): User? {
        if (dao.getUser(id) != null){
            return mapper.mapDbModelToEntity(dao.getUser(id))
        }
        return null
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user.id)
    }

    override suspend fun update(user: User) = dao.update(mapper.mapEntityToDbModel(user))


    override suspend fun addWords(words: Words) {
        wordsDao.addWords(wordsMapper.mapEntityToDbModel(words))
    }

    override suspend fun deleteWords(level: String) {
        wordsDao.deleteWords(level)
    }

    override suspend fun getWords(): List<Words> =
        wordsMapper.mapListDbModelToListEntity(wordsDao.getWords())

    override suspend fun getWord(level: String): Words? {
        val wordsDbModel = wordsDao.getWord(level)
        return wordsDbModel?.let { wordsMapper.mapDbModelToEntity(it) }
    }

    override suspend fun updateWords(words: Words) {
        wordsDao.updateWords(wordsMapper.mapEntityToDbModel(words))
    }

}