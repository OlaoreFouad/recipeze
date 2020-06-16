package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseDiet

data class Preference(
    var name: String,

    var hasMore: Boolean = false,

    var isSelected: Boolean = false,

    var details: String? = null,

    var imageUri: String? = null
) {

    constructor(preference: DatabaseDiet): this(preference.name, true, details = preference.details, imageUri = preference.imageUri)

    constructor(preference: DatabaseCuisine): this(preference.name, false)

}