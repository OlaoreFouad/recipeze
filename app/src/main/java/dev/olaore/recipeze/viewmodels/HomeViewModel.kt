package dev.olaore.recipeze.viewmodels

import android.app.Application
import androidx.lifecycle.*
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository

class HomeViewModel(
    private val usersRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private val _user = usersRepository.user
    val user: LiveData<User>
        get() = _user

}