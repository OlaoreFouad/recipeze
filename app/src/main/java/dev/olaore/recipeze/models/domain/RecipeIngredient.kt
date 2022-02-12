package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.database.DatabaseRecipeIngredient

data class RecipeIngredient(
    var id: Int,
    var name: String,
    var originalName: String,
    var original: String,
    var unit: String,
    var measures: String
)

fun RecipeIngredient.mapToEntity(recipeId: Int, index: Int): DatabaseRecipeIngredient {
    return DatabaseRecipeIngredient(
        recipeId = recipeId,
        name = name,
        originalName = originalName,
        original = original,
        ingredientIndex = index,
        unit = unit,
        measures = measures
    )
}