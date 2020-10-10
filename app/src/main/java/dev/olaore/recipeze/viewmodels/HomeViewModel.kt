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
import dev.olaore.recipeze.models.mappers.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val usersRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    val user = usersRepository.user

    var randomRecipes = MutableLiveData<Result<List<Recipe>>>()

    fun getRandomRecipes(tags: String) {
        viewModelScope.launch {
            randomRecipes.value = (recipesRepository.getRandomRecipes(tags, 10) as MutableLiveData<Result<List<Recipe>>>).value
        }
    }


}