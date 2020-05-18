package dev.olaore.recipeze.models.database

data class DatabaseRecipeInstructionData(
    var id: Int,
    var instructionId: Int,
    var recipeId: Int,
    var name: String
)