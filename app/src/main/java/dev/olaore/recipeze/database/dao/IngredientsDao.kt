package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.olaore.recipeze.models.database.DatabaseRecipeIngredient

@Dao
interface IngredientsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIngredients(ingredients: List<DatabaseRecipeIngredient>)

    @Delete
    fun deleteIngredients(ingredients: List<DatabaseRecipeIngredient>)

    @Query("SELECT * FROM recipes_ingredients_table WHERE recipeId = :recipeId ORDER BY ingredientIndex")
    fun getIngredientsForRecipe(recipeId: Int): LiveData<List<DatabaseRecipeIngredient>>

}