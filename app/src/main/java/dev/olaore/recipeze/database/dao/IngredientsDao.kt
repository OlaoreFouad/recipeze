package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.olaore.recipeze.models.database.DatabaseRecipeIngredient

@Dao
interface IngredientsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIngredients(ingredients: List<DatabaseRecipeIngredient>)

    @Query("SELECT * FROM recipes_ingredients_table WHERE recipeId = :recipeId ORDER BY ingredientIndex")
    fun getIngredientsForRecipe(recipeId: Int): LiveData<List<DatabaseRecipeIngredient>>

}