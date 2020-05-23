package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.olaore.recipeze.models.database.DatabaseRecipeInstructionData

@Dao
interface InstructionsDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInstructionsData(vararg data: DatabaseRecipeInstructionData)

    @Query(
        "SELECT * FROM recipes_instruction_data_table WHERE instructionId = :instructionsId AND recipeId = :recipeId"
    )
    fun getInstructionsDataForRecipe(
        recipeId: Int, instructionsId: Int
    ): LiveData<List<DatabaseRecipeInstructionData>>

}