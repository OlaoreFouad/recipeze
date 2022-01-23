package dev.olaore.recipeze.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.RecentSearch
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
    var searchedRecipes = MutableLiveData<Event<Resource<List<RecipeSearch>>>>()
    var recentSearches: LiveData<List<RecentSearch>> = userRepository.recentSearches

    fun search(query: String) {

        searchedRecipes.postValue(Event(Resource.loading()))

        viewModelScope.launch {
            try {
                val searchedRecipesResult = recipesRepository.searchRecipes(query).results.convertToDomainSearches()
                saveSearchQuery(query)
                searchedRecipes.postValue(Event(Resource.success(searchedRecipesResult)))
            } catch (exception: Exception) {
                searchedRecipes.postValue(Event(Resource.error("Error occurred: " + exception.message)))
            }
        }

    }

    private fun saveSearchQuery(query: String) {
        viewModelScope.launch {
            userRepository.saveRecentSearch(query)
        }
    }

    fun deleteRecentSearch(id: String) {
        viewModelScope.launch {
            userRepository.deleteRecentSearch(id)
        }
    }

}