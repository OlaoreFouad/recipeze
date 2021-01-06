package dev.olaore.recipeze.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dev.olaore.recipeze.database.RecipesDatabase
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.mappers.asDomainModel
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.network.Network
import dev.olaore.recipeze.network.RecipesApiHelper
import dev.olaore.recipeze.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class RecipesRepository(
    private val database: RecipesDatabase,
    private val recipesHelper: RecipesApiHelper
) {

    suspend fun getRandomRecipes() = recipesHelper.getAllRecipes()

    suspend fun refreshRecipes(tag: String) = recipesHelper.getRecipesWithTag(tag)

    suspend fun getRecipeDetails(id: Int) = recipesHelper.getRecipeDetails(id)

    suspend fun getRecipeSummary(id: Int) = recipesHelper.getRecipeSummary(id)

}