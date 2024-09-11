package com.test.android.technicalinterviewapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.android.technicalinterviewapp.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
 @Query("SELECT * from user_table")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertUser(user: User)

     @Delete
     suspend fun deleteUser(user: User)
}