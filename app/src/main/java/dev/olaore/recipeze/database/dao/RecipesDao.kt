package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.olaore.recipeze.models.database.DatabaseRecipe

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: DatabaseRecipe)

    @Delete
    suspend fun delete(recipe: DatabaseRecipe)

    @Query("SELECT * FROM recipes_table")
    fun getRecipes(): LiveData<List<DatabaseRecipe>>

    @Query("SELECT * FROM recipes_table WHERE id = :id")
    suspend fun getRecipe(id: Int): DatabaseRecipe

}