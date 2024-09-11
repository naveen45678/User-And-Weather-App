package com.test.android.technicalinterviewapp.data.repository

import androidx.lifecycle.LiveData
import com.test.android.technicalinterviewapp.data.local.UserDao
import com.test.android.technicalinterviewapp.data.local.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao){
     fun  getAllUsers():Flow<List<User>> = userDao.getAllUsers()
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}