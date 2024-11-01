package com.example.linguistic.data

import android.app.Application
import com.example.linguistic.domain.Repository
import com.example.linguistic.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val application: Application): Repository {

    val mapper = UserMapper()
    val dao = AppDatabase.getInstance(application).userDao()

    override suspend fun addUser(user: User) {
        dao.addUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun getUsers(): Flow<List<User>> = dao.getUsers().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getUser(id: Int): User {
        return mapper.mapDbModelToEntity(dao.getUser(id))
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user.id)
    }

    override suspend fun update(user: User) = dao.update(mapper.mapEntityToDbModel(user))
}