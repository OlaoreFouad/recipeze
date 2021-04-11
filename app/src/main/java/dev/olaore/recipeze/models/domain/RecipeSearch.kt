package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.network.NetworkRecipeSearch

data class RecipeSearch(
    var id: Int,
    var image: String,
    var title: String
)