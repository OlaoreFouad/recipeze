package dev.olaore.recipeze.models.mappers

import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseDiet
import dev.olaore.recipeze.models.database.DatabaseRecipe
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.network.NetworkRecipeSearchContainer
import dev.olaore.recipeze.models.network.NetworkRecipeSummary

fun NetworkRecipeSearchContainer.asDomainModel(): List<Recipe> {

    return results.map {
        Recipe(it, getRecipeSummary(it.id)?.summary!!)
    }

}

fun NetworkRecipeSearchContainer.asDatabaseModel(): List<DatabaseRecipe> {
    return results.map {
        DatabaseRecipe(it, getRecipeSummary(it.id)?.summary!!)
    }
}

fun List<DatabaseRecipe>.asDomainModel(): List<Recipe> {
    return map {
        Recipe(
            it.id, it.imageUri, it.readyInMinutes, it.title, true, it.summary
        )
    }
}

fun List<DatabaseDiet>.asPreferenceDietDomainModel(): List<Preference> {
    return map { Preference(it) }
}

fun List<DatabaseCuisine>.asPreferenceCuisineDomainModel(): List<Preference> {
    return map { Preference(it) }
}

fun getRecipeSummary(id: Int): NetworkRecipeSummary? {
    return null
}