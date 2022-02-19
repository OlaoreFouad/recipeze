package dev.olaore.recipeze.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dev.olaore.recipeze.database.RecipesDatabase
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.domain.RecipeIngredient
import dev.olaore.recipeze.models.domain.RecipeInstruction
import dev.olaore.recipeze.models.domain.mapToEntity
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

    suspend fun getRecipeInstruction(id: Int) = recipesHelper.getRecipeInstructions(id)

    suspend fun searchRecipes(query: String) = recipesHelper.searchRecipe(query)

    suspend fun getRecipeById(id: Int) = database.recipesDao.getRecipe(id)

    suspend fun favoriteRecipe(
        recipe: Recipe
    ) {
        database.recipesDao.insert(recipe.mapToEntity())

        val ings = recipe.ingredients.mapIndexed { index, recipeIngredient ->
            recipeIngredient.mapToEntity(recipe.id!!, index)
        }

        database.ingredientsDao.addIngredients(ings)

        val insts = recipe.instructions.map {
            it.mapToEntity(recipe.id!!)
        }
        database.instructionsDao.addInstructions(insts)

    }

    suspend fun removeFavoriteRecipe(
        recipe: Recipe
    ) {
        database.recipesDao.delete(recipe.mapToEntity())

        val ings = recipe.ingredients.mapIndexed { index, recipeIngredient ->
            recipeIngredient.mapToEntity(recipe.id!!, index)
        }

        database.ingredientsDao.deleteIngredients(ings)

        val insts = recipe.instructions.map {
            it.mapToEntity(recipe.id!!)
        }
        database.instructionsDao.deleteInstructions(insts)

    }

}