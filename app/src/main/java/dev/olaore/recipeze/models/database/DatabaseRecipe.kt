package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.olaore.recipeze.models.network.NetworkRecipeSearch

@Entity(tableName = "recipes_table")
data class DatabaseRecipe(

    @PrimaryKey
    var id: Int,

    var title: String,

    var imageUri: String,

    var readyInMinutes: Int,

    var summary: String,

    var types: String
) {

    constructor(netRecipe: NetworkRecipeSearch, summary: String) : this(
        netRecipe.id, netRecipe.title, "", 0,
        summary, ""
    )

}