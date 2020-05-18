package dev.olaore.recipeze.models.database

data class DatabaseRecipe(
    var id: Int,
    var title: String,
    var imageUri: String,
    var readyInMinutes: Int,
    var summary: String,
    var types: String
)