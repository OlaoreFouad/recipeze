package dev.olaore.recipeze.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.olaore.recipeze.database.dao.IngredientsDao
import dev.olaore.recipeze.database.dao.InstructionsDao
import dev.olaore.recipeze.database.dao.InstructionsDataDao
import dev.olaore.recipeze.database.dao.RecipesDao
import dev.olaore.recipeze.models.database.DatabaseRecipe
import dev.olaore.recipeze.models.database.DatabaseRecipeIngredient
import dev.olaore.recipeze.models.database.DatabaseRecipeInstruction
import dev.olaore.recipeze.models.database.DatabaseRecipeInstructionData

@Database(
    entities = [
        DatabaseRecipe::class, DatabaseRecipeIngredient::class,
        DatabaseRecipeInstruction::class, DatabaseRecipeInstructionData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RecipesDatabase : RoomDatabase() {

    abstract val recipesDao: RecipesDao
    abstract val ingredientsDao: IngredientsDao
    abstract val instructionsDao: InstructionsDao
    abstract val instructionsDataDao: InstructionsDataDao

}

private lateinit var INSTANCE: RecipesDatabase

fun getDatabase(context: Context): RecipesDatabase {

    if (!::INSTANCE.isInitialized) {
        synchronized(RecipesDatabase::class) {
            INSTANCE = Room.databaseBuilder(
                context, RecipesDatabase::class.java, "recipes_db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    return INSTANCE

}