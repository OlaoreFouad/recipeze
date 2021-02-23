package dev.olaore.recipeze.models.network

data class NetworkRecipeInformation(
    var id: Int,
    var title: String,
    var image: String,
    var readyInMinutes: Int,
    var dishTypes: List<String?>?,
    var sourceName: String?,
    var sourceUrl: String?,
    var license: String?,
    var occassions: List<String?>?,
    var extendedIngredients: List<NetworkRecipeIngredient>,
    var creditsText: String,
    var servings: Int,
    var aggregateLikes: Int
)

data class NetworkRecipeRandomContainer(
    var recipes: List<NetworkRecipeInformation>
)