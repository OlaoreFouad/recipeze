package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.mappers.asDomainModel
import dev.olaore.recipeze.models.mappers.asRecipeInstructionDomainModel
import dev.olaore.recipeze.models.network.NetworkRecipeInformation
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
    var instructions: MutableList<RecipeInstruction> = mutableListOf(),
    var occassions: String? = "",
    var dishTypes: String? = "",
    var sourceName: String? = "",
    var sourceUrl: String? = "",
    var license: String? = ""

) {
    

    constructor(netRecipe: NetworkRecipeSearch, summary: String) : this(
        netRecipe.id,
        netRecipe.image,
        0,
        netRecipe.title,
        false, 0, summary
    )

    constructor(
        recipe: NetworkRecipeInformation?,
        summary: String,
        networkInstructions: MutableList<NetworkRecipeInstruction> = mutableListOf()
    ) :  this(
        recipe?.id,
        recipe?.image,
        recipe?.readyInMinutes,
        recipe?.title,
        false,
        recipe?.aggregateLikes,
        summary,
        recipe!!.extendedIngredients.asDomainModel(recipe.id).toMutableList(),
        networkInstructions.asRecipeInstructionDomainModel(recipe.id).toMutableList(),
        recipe?.occassions?.joinToString(),
        recipe?.dishTypes?.joinToString(),
        recipe?.sourceName,
        recipe?.sourceUrl,
        recipe?.license
    )

    fun getRecipeDetails(): RecipeDetails {
        return RecipeDetails(this.occassions, this.dishTypes, this.sourceName, this.sourceUrl, this.license, this.summary!!)
    }

}

data class RecipeDetails(
    var occassions: String? = "",
    var dishTypes: String? = "",
    var sourceName: String? = "",
    var sourceUrl: String? = "",
    var license: String? = "",
    var summary: String = ""
)