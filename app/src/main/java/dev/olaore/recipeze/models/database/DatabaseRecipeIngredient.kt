package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes_ingredients_table",
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
data class DatabaseRecipeIngredient(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var recipeId: Int,

    var name: String,

    var originalName: String,

    var original: String,

    var ingredientIndex: Int,

    var unit: String,

    var measures: String

)