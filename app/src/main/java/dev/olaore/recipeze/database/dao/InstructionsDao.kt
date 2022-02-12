package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.olaore.recipeze.models.database.DatabaseRecipeInstruction

@Dao
interface InstructionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInstructions(instructions: List<DatabaseRecipeInstruction>)

    @Query("SELECT * FROM recipes_instructions_table WHERE recipeId = :recipeId")
    fun getInstructionsForRecipe(recipeId: Int): LiveData<List<DatabaseRecipeInstruction>>

}