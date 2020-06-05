package dev.olaore.recipeze.models.domain

data class User(
    var username: String? = "",

    var pin: String? = "",

    var cuisines: String = "",

    var diets: String = ""
)