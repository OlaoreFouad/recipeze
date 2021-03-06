package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class DatabaseUser(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var username: String,

    var diets: String,

    var cuisines: String,

    var pin: String

)

@Entity(tableName = "diets_table")
data class DatabaseDiet(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var name: String,

    var details: String,

    var imageUri: String

)

@Entity(tableName = "cuisines_table")
data class DatabaseCuisine(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var name: String

)