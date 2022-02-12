package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
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
    ],
    indices = [Index("recipeId")]
)
data class DatabaseRecipeInstruction(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var recipeId: Int,

    var number: Int,

    var step: String
)