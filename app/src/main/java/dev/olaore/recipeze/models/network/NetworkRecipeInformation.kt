package dev.olaore.recipeze.models.network

data class NetworkRecipeInformation(
    var id: Int,
    var title: String,
    var image: String,
    var readyInMinutes: Int,
    var dishTypes: List<String>,
    var sourceName: String,
    var extendedIngredients: List<NetworkRecipeIngredient>
)

data class NetworkRecipeRandomContainer(
    var recipes: List<NetworkRecipeInformation>
)