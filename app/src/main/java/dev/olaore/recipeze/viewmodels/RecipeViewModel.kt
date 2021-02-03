package dev.olaore.recipeze.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.domain.RecipeIngredient
import dev.olaore.recipeze.models.mappers.Resource
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val usersRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    var recipeId: Int = 0
    var recipe = MutableLiveData<Resource<Recipe>>()
    var ingredients = MutableLiveData<List<RecipeIngredient>>()

    fun retrieveRecipeDetails() {
        viewModelScope.launch {

            recipe.postValue(Resource.loading<Recipe>())

            try {
                val networkRecipeDetails = recipesRepository.getRecipeDetails(recipeId)
                val networkRecipeSummary = recipesRepository.getRecipeSummary(recipeId).summary
                val networkRecipeInstructions = recipesRepository.getRecipeInstruction(recipeId).get(0).steps

                val finalRecipe = Recipe(networkRecipeDetails, networkRecipeSummary, networkRecipeInstructions.toMutableList())

                recipe.postValue(Resource.success(finalRecipe))
                ingredients.postValue(finalRecipe.ingredients)
            } catch (ex: Exception) {
                recipe.postValue(Resource.error<Recipe>("Error occurred while getting recipes: ${ ex.message }"))
            }

        }
    }

}