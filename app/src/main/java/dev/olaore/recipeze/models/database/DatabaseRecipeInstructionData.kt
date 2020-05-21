package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes_instruction_data_table",
    foreignKeys = [
        ForeignKey(
            entity = DatabaseRecipeInstruction::class,
            parentColumns = ["id"],
            childColumns = ["instructionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DatabaseRecipe::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DatabaseRecipeInstructionData(

    @PrimaryKey
    var id: Int,

    var instructionId: Int,

    var recipeId: Int,

    var name: String,

    var isEquipment: Boolean
)