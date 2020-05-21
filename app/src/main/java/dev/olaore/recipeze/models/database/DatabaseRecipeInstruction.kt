package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes_instructions_table",
    foreignKeys = [
        ForeignKey(
            entity = DatabaseRecipe::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DatabaseRecipeInstruction(

    @PrimaryKey
    var id: Int,

    var recipeId: Int,

    var number: Int,

    var step: String
)