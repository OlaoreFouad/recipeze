package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.network.NetworkRecipeInformation
import dev.olaore.recipeze.models.network.NetworkRecipeSearch

data class Recipe(

    var id: Int? = 0,
    var imageUri: String? = "",
    var readyInMinutes: Int? = 0,
    var title: String? = "",
    var isLocal: Boolean? = false,
    var likes: Int? = 0,
    var summary: String? = ""

) {

    constructor(netRecipe: NetworkRecipeSearch, summary: String) : this(
        netRecipe.id, netRecipe.image, netRecipe.readyInMinutes, netRecipe.title,
        false, 0, summary
    )

    constructor(randomNetRecipe: NetworkRecipeInformation?, summary: String) :  this(
        randomNetRecipe?.id, randomNetRecipe?.image, randomNetRecipe?.readyInMinutes,
        randomNetRecipe?.title, false, randomNetRecipe?.aggregateLikes, summary
    )

}