package dev.olaore.recipeze.models.mappers

import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseDiet
import dev.olaore.recipeze.models.database.DatabaseRecipe
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.models.network.NetworkRecipeSearchContainer
import dev.olaore.recipeze.models.network.NetworkRecipeSummary

fun NetworkRecipeSearchContainer.asDomainModel(): List<Recipe> {

    return results.map {
        Recipe(it, getRecipeSummary(it.id)?.summary!!)
    }

}

fun NetworkRecipeRandomContainer.asDomainModel(): List<Recipe> {

    return recipes.map {
        Recipe(it)
    }

}

fun NetworkRecipeSearchContainer.asDatabaseModel(): List<DatabaseRecipe> {
    return results.map {
        DatabaseRecipe(it, getRecipeSummary(it.id)?.summary!!)
    }
}

fun DatabaseUser.asDomainModel() = User(id, username, pin, cuisines, diets)

fun User.asDatabaseModel() = DatabaseUser(username = username!!, diets = diets, cuisines = cuisines, pin = pin!!)

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

sealed class Result<out T: Any> {
    data class SUCCESS<out T: Any>(val data: T): Result<T>()
    data class ERROR(val message: String): Result<Nothing>()
    data class LOADING<out T: Any>(val data: T? = null): Result<Nothing>()
}