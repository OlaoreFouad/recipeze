package dev.olaore.recipeze.models.domain

import android.util.Log
import dev.olaore.recipeze.models.mappers.asDomainModel
import dev.olaore.recipeze.models.mappers.convertToString
import dev.olaore.recipeze.models.network.NetworkRecipeInformation
import dev.olaore.recipeze.models.network.NetworkRecipeIngredient
import dev.olaore.recipeze.models.network.NetworkRecipeInstruction
import dev.olaore.recipeze.models.network.NetworkRecipeSearch

data class Recipe(

    var id: Int? = 0,
    var imageUri: String? = "",
    var readyInMinutes: Int? = 0,
    var title: String? = "",
    var isLocal: Boolean? = false,
    var likes: Int? = 0,
    var summary: String? = "",
    var ingredients: MutableList<RecipeIngredient> = mutableListOf(),
    var instructions: MutableList<RecipeInstruction> = mutableListOf()

) {
    

    constructor(netRecipe: NetworkRecipeSearch, summary: String) : this(
        netRecipe.id, netRecipe.image, netRecipe.readyInMinutes, netRecipe.title,
        false, 0, summary, mutableListOf(), mutableListOf()
    )

    constructor(
        randomNetRecipe: NetworkRecipeInformation?,
        summary: String,
        networkInstructions: MutableList<NetworkRecipeInstruction> = mutableListOf()
    ) :  this(
        randomNetRecipe?.id,
        randomNetRecipe?.image,
        randomNetRecipe?.readyInMinutes,
        randomNetRecipe?.title,
        false,
        randomNetRecipe?.aggregateLikes,
        summary,
        randomNetRecipe!!.extendedIngredients.asDomainModel(randomNetRecipe.id).toMutableList()
    )

}