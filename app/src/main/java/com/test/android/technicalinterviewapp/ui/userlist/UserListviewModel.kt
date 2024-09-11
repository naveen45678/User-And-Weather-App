package com.test.android.technicalinterviewapp.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.test.android.technicalinterviewapp.data.local.entity.User
import com.test.android.technicalinterviewapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListviewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val allUsers: LiveData<List<User>> = userRepository.getAllUsers().asLiveData()

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }


}