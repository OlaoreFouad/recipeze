package dev.olaore.recipeze.models.network

data class NetworkRecipeSearch(
    var id: Int,
    var image: String,
    var imageUrls: List<String>,
    var readyInMinutes: Int,
    var title: String
)

data class NetworkRecipeSearchContainer(
    var offset: Int,
    var number: Int,
    var results: List<NetworkRecipeSearch>,
    var totalResults: Int
)