package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val usersRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private val _user = usersRepository.user
    val user: LiveData<User>
        get() = _user

    private var _randomRecipes: LiveData<NetworkRecipeRandomContainer>? = null
    val randomRecipes: LiveData<NetworkRecipeRandomContainer>
        get() = _randomRecipes!!

    init {
        viewModelScope.launch {
            _randomRecipes = recipesRepository.getRandomRecipes("ALL", 10)
        }
    }


}