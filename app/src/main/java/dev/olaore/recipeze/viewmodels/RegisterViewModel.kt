package dev.olaore.recipeze.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.repositories.UsersRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RegisterViewModel(val app: Application) : AndroidViewModel(app) {

    private val user = User()

    fun createUser(username: String, pin: String) {
        user.username = username
        user.pin = pin
    }

    fun getUser() = user

}