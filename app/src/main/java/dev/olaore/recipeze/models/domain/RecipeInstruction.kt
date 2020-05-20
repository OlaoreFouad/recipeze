package dev.olaore.recipeze.models.domain

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