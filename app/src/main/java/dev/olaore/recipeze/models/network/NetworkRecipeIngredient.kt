package dev.olaore.recipeze.models.network

data class NetworkRecipeIngredient (
    var aisle: String,
    var amount: Float,
    var consitency: String,
    var id: Int,
    var image: String,
    var measures: NetworkRecipeIngredientMeasureContainer,
    var name: String,
    var original: String,
    var unit: String,
    var originalName: String
)

data class NetworkRecipeIngredientMeasure(
    var amount: Float,
    var unitLong: String,
    var unitShort: String
)

data class NetworkRecipeIngredientMeasureContainer(
    var metric: NetworkRecipeIngredientMeasure,
    var us: NetworkRecipeIngredientMeasure
)