package dev.olaore.recipeze.models.network

data class NetworkRecipeInstructionContainer (
    var name: String,
    var steps: List<NetworkRecipeInstruction>
)

data class NetworkRecipeInstruction(
    var equipment: List<NetworkRecipeInstructionMetadata>,
    var ingredients: List<NetworkRecipeInstructionMetadata>,
    var length: List<NetworkRecipeInstructionLength>,
    var number: Int,
    var step: String
)

data class NetworkRecipeInstructionLength(
    var number: Int,
    var unit: String
)

data class NetworkRecipeInstructionMetadata(
    var id: Int,
    var image: String,
    var name: String
)