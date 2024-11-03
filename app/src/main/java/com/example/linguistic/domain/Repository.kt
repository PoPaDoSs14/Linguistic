package com.example.linguistic.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addUser(user: User)

    suspend fun getUsers(): Flow<List<User>>

    suspend fun getUser(id: Int): User?

    suspend fun deleteUser(user: User)

    suspend fun update(user: User)
}