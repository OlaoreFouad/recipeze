package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.database.DatabaseRecipeInstruction
import dev.olaore.recipeze.models.database.DatabaseRecipeInstructionData

data class RecipeInstruction(

    var id: Int,
    var number: Int,
    var step: String,
    var equipments: List<RecipeInstructionData>,
    var ingredient: List<RecipeInstructionData>

)

data class RecipeInstructionData(

    var id: Int,
    var name: String

)

fun RecipeInstruction.mapToEntity(recipeId: Int): DatabaseRecipeInstruction {
    return DatabaseRecipeInstruction(
        recipeId = recipeId,
        number = number,
        step = step
    )
}

fun RecipeInstructionData.mapToEntity(
    recipeId: Int,
    instructionId: Int,
    isEquipment: Boolean
): DatabaseRecipeInstructionData {
    return DatabaseRecipeInstructionData(
        this.id,
        recipeId,
        instructionId,
        this.name,
        isEquipment
    )
}