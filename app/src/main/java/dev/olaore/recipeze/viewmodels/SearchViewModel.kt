package dev.olaore.recipeze.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val userRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    var user: LiveData<User> = userRepository.user

}