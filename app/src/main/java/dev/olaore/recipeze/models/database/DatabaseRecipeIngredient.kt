package dev.olaore.recipeze.models.database

data class DatabaseRecipeIngredient(
    var id: Int,
    var recipeId: Int,
    var name: String,
    var originalName: String,
    var original: String,
    var ingredientIndex: Int,
    var unit: String,
    var measures: String
)