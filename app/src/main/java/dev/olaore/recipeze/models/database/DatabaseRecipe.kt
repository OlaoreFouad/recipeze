package dev.olaore.recipeze.models.database

import dev.olaore.recipeze.models.network.NetworkRecipeSearch

data class DatabaseRecipe(
    var id: Int,
    var title: String,
    var imageUri: String,
    var readyInMinutes: Int,
    var summary: String,
    var types: String
) {

    constructor(netRecipe: NetworkRecipeSearch, summary: String) : this(
        netRecipe.id, netRecipe.title, "", netRecipe.readyInMinutes,
        summary, ""
    )

}