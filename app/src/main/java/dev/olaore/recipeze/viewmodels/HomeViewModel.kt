package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.Recipe
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

    private var _randomRecipes: LiveData<List<Recipe>>? = null
    val randomRecipes: LiveData<List<Recipe>>
        get() = _randomRecipes!!

    init {
        viewModelScope.launch {
            _randomRecipes = recipesRepository.getRandomRecipes("ALL", 10)
        }
    }

    fun getRandomRecipes(tags: String = "ALL") {
        Log.d("HomeViewModel", tags)
        viewModelScope.launch {
            _randomRecipes = recipesRepository.getRandomRecipes(tags, 10)
        }
    }


}