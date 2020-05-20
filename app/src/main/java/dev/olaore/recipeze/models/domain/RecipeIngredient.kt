package dev.olaore.recipeze.models.domain

data class RecipeIngredient(
    var id: Int,
    var name: String,
    var originalName: String,
    var original: String,
    var unit: String,
    var measures: String
)