package dev.olaore.recipeze.models.mappers

import dev.olaore.recipeze.models.database.DatabaseRecipe
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

fun getRecipeSummary(id: Int): NetworkRecipeSummary? {
    return null
}