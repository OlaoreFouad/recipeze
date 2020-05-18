package dev.olaore.recipeze.models.database

data class DatabaseRecipeInstruction(
    var id: Int,
    var recipeId: Int,
    var number: Int,
    var step: String,
    var equipmentsId: Int,
    var ingredientsId: Int
)