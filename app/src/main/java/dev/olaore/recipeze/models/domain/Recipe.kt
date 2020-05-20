package dev.olaore.recipeze.models.domain

data class Recipe(

    var id: Int,
    var imageUri: String,
    var readyInMinutes: Int,
    var title: String,
    var isLocal: Boolean,
    var summary: String

)