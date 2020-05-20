package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.network.NetworkRecipeSearch

data class Recipe(

    var id: Int,
    var imageUri: String,
    var readyInMinutes: Int,
    var title: String,
    var isLocal: Boolean,
    var summary: String

) {

    constructor(netRecipe: NetworkRecipeSearch, summary: String) : this(
        netRecipe.id, netRecipe.image, netRecipe.readyInMinutes, netRecipe.title,
        false, summary
    )

}