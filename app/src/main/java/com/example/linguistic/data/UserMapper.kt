package com.example.linguistic.data

import com.example.linguistic.domain.User

class UserMapper {

    fun mapEntityToDbModel(user: User): UserDbModel{
        return UserDbModel(
            id = user.id,
            name = user.name,
            countOfWord = user.countOfWord,
            rating = user.rating,
            avatar = user.avatar
        )
    }

    fun mapDbModelToEntity(userDbModel: UserDbModel): User {
        return User(
            id = userDbModel.id,
            name = userDbModel.name,
            countOfWord = userDbModel.countOfWord,
            rating = userDbModel.rating,
            avatar = userDbModel.avatar
        )
    }


    fun mapListDbModelToListEntity(list: List<UserDbModel>): List<User> = list.map {
        mapDbModelToEntity(it)
    }
}