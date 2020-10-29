package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.mappers.Resource
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository
import dev.olaore.recipeze.models.mappers.asDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val usersRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    val user = usersRepository.user

    private var _randomRecipes = MutableLiveData<Resource<List<Recipe>>>()
    val randomRecipes: LiveData<Resource<List<Recipe>>>
        get() = _randomRecipes

    fun getRandomRecipes() {
        viewModelScope.launch {
            _randomRecipes.postValue(Resource.loading<List<Recipe>>())
            try {
                _randomRecipes.postValue(Resource.success(recipesRepository.getRandomRecipes().asDomainModel()))
            } catch (e: Exception) {
                _randomRecipes.postValue(Resource.error<List<Recipe>>(e.message ?: "Error Occurred"))
            }
        }
    }

    fun refreshRecipes(tags: String) {
        viewModelScope.launch {
            _randomRecipes.postValue(Resource.loading<List<Recipe>>())
            try {
                _randomRecipes.postValue(Resource.success(recipesRepository.refreshRecipes(tags).asDomainModel()))
            } catch (e: Exception) {
                _randomRecipes.postValue(Resource.error<List<Recipe>>(e.message ?: "Error Occurred"))
            }
        }
    }


}