package dev.olaore.recipeze.models.mappers

import dev.olaore.recipeze.models.database.*
import dev.olaore.recipeze.models.domain.*
import dev.olaore.recipeze.models.network.*

fun NetworkRecipeSearchContainer.asDomainModel(): List<Recipe> {

    return results.map {
        Recipe(it, getRecipeSummary(it.id)?.summary!!)
    }

}

fun NetworkRecipeRandomContainer.asDomainModel(): List<Recipe> {

    return recipes.map {
        Recipe(it, "")
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
            it.id, it.imageUri, it.readyInMinutes, it.title, true, 0, it.summary
        )
    }
}

fun List<DatabaseDiet>.asPreferenceDietDomainModel(): List<Preference> {
    return map { Preference(it) }
}

fun List<DatabaseCuisine>.asPreferenceCuisineDomainModel(): List<Preference> {
    return map { Preference(it) }
}

fun NetworkRecipeIngredientMeasureContainer.convertToString(): String {
    return "${ this.metric.amount } ${ this.metric.unitLong }"
}

fun List<NetworkRecipeIngredient>.asDomainModel(id: Int): List<RecipeIngredient> {
    return map { ingredient ->
        RecipeIngredient(
            id, ingredient.name, ingredient.originalName, ingredient.original, ingredient.unit, ingredient.measures.convertToString()
        )
    }
}

fun List<NetworkRecipeInstruction>.asRecipeInstructionDomainModel(id: Int): List<RecipeInstruction> {
    return map { instruction ->
        RecipeInstruction(
            id, instruction.number,
            instruction.step,
            instruction.equipment.asRecipeInstructionDataDomainModel(),
            instruction.ingredients.asRecipeInstructionDataDomainModel()
        )
    }
}

fun List<NetworkRecipeInstructionMetadata>.asRecipeInstructionDataDomainModel(): List<RecipeInstructionData> {
    return map { instructionMetaData ->
        RecipeInstructionData(
            instructionMetaData.id, instructionMetaData.name
        )
    }
}

fun List<DatabaseRecentSearch>.asDomainRecentSearches(): List<RecentSearch> {
    return map {
        RecentSearch(it.id, it.content)
    }
}

fun getRecipeSummary(id: Int): NetworkRecipeSummary? {
    return null
}

enum class Status {
    LOADING, ERROR, SUCCESS
}

class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {

        fun <T> success(data: T?) = Resource(Status.SUCCESS, data)

        fun error(message: String) = Resource(Status.ERROR, null, message)

        fun loading() = Resource(Status.LOADING, null)

    }
}

fun List<NetworkRecipeSearch>.convertToDomainSearches(): List<RecipeSearch> {
    return map {
        RecipeSearch(it.id, it.image, it.title)
    }
}

fun DatabaseRecentSearch.asDomainModel(): RecentSearch = RecentSearch(id, content)