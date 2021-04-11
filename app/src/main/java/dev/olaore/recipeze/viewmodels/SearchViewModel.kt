package dev.olaore.recipeze.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.RecipeSearch
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.mappers.Resource
import dev.olaore.recipeze.models.mappers.convertToDomainSearches
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val userRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    var user: LiveData<User> = userRepository.user
    var searchedRecipes = MutableLiveData<Resource<List<RecipeSearch>>>()


    fun search(query: String) {

        searchedRecipes.postValue(Resource.loading<Any>())

        viewModelScope.launch {
            try {
                val searchedRecipesResult = recipesRepository.searchRecipes(query).results.convertToDomainSearches()
                searchedRecipes.postValue(Resource.success(searchedRecipesResult))
            } catch (exception: Exception) {
                searchedRecipes.postValue(Resource.error<Any>("Error occurred: " + exception.message))
            }
        }

    }

}