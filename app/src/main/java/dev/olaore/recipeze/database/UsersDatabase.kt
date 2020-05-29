package dev.olaore.recipeze.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.olaore.recipeze.database.dao.UsersDao
import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseDiet
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.database.SearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [DatabaseUser::class, DatabaseDiet::class, DatabaseCuisine::class, SearchResult::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}

private lateinit var INSTANCE: UsersDatabase

fun getUsersDatabase(application: Application): UsersDatabase {

    if (!::INSTANCE.isInitialized) {
        INSTANCE = Room.databaseBuilder(
            application.applicationContext, UsersDatabase::class.java, "users_db"
        ).fallbackToDestructiveMigration().addCallback(
            object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

//                    all diets
                    val diets = arrayOf(
                        DatabaseDiet(
                            name = "Vegetarian",
                            details = "Vegetarian diets contain various levels of fruit" +
                                ", veges, grains, pulses, nuts and seeds",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot.com" +
                                    "/o/recipeze%2Fvegetarian.jpg?alt=media&token=283ba28a-ce95-4dc9-9682-a5fdda8533af"
                        ),
                        DatabaseDiet(
                            name = "Lacto-Vegetarian",
                            details = "This diet avoid animal flesh and egg" +
                                "s, but dairy products can be consumed.",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot.com" +
                                    "/o/recipeze%2Flacto_vegetarian.jpg?alt=media&token=41af5e5c-b968-4299-8b19-bee6f734a378"
                        ),
                        DatabaseDiet(
                            name = "Vegan",
                            details = "This is a vegetarian diet that avoid all animal" +
                                "-derived product.",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot.com" +
                                    "/o/recipeze%2Fvegan.jpg?alt=media&token=05f0e64c-11a6-4b54-b61d-4c77fe130ccf"
                        ),
                        DatabaseDiet(
                            name = "Ovo-Vegetarian",
                            details = "This diet avoids all animal products except" +
                                " eggs e.g meat, poultry, fish, cheese and milk.",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot.com" +
                                    "/o/recipeze%2Fovo_vegetarian.jpg?alt=media&token=b3da14c0-7717-4a86-a92e-a2a042eb091f"
                        ),
                        DatabaseDiet(
                            name = "Paleo",
                            details = "Paleolithic humans' diets vary depending on availability" +
                                " and location. The basic concept of the paleo diet is to eat whole foods and avoid " +
                                "all processed food.",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot.com" +
                                    "/o/recipeze%2Fpaleo.jpg?alt=media&token=98996f05-cfd6-4dee-8dc1-b97782c72c97"
                        ),
                        DatabaseDiet(
                            name = "Primal",
                            details = "On the primal diet, you can eat various types of meat and " +
                                "fish, fruits, vegetables, fruits and seeds.",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot" +
                                    ".com/o/recipeze%2Fprimal.jpg?alt=media&token=0a9cb23d-fc77-46e7-b1a8-e19bd1fd7bc6"
                        ),
                        DatabaseDiet(
                            name = "Ketogenic",
                            details = "The Keto diet is a low-carb, high-fat diet. It lowers " +
                                "blood sugar and insulin levels, and shifts the body's metabolism away from carbs " +
                                "and towards fat and ketones.",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1" +
                                    ".appspot.com/o/recipeze%2Fketo.jpg?alt=media&token=1ac827b0-4516-486c-981b-20ef57b24d4d"
                        ),
                        DatabaseDiet(
                            name = "Gluten-Free",
                            details = "Gluten is a family of proteins that is found in certain" +
                                " grains. Eating it causes harmful effects in people with celiac disease and gluten" +
                                " sensitivity",
                            imageUri = "https://firebasestorage.googleapis.com/v0/b/images-c90a1.appspot" +
                                ".com/o/recipeze%2Fgluten.jpg?alt=media&token=be48fb15-0169-4bfc-a820-5fc07d44b634"
                        )
                    )

//                     all cuisines
                    val cuisines = arrayOf(
                        DatabaseCuisine(name = "African"),
                        DatabaseCuisine(name = "American"),
                        DatabaseCuisine(name = "British"),
                        DatabaseCuisine(name = "Cajun"),
                        DatabaseCuisine(name = "Caribbean"),
                        DatabaseCuisine(name = "Chinese"),
                        DatabaseCuisine(name = "Eastern European"),
                        DatabaseCuisine(name = "European"),
                        DatabaseCuisine(name = "French"),
                        DatabaseCuisine(name = "German"),
                        DatabaseCuisine(name = "Greek"),
                        DatabaseCuisine(name = "Indian"),
                        DatabaseCuisine(name = "Irish"),
                        DatabaseCuisine(name = "Italian"),
                        DatabaseCuisine(name = "Japanese"),
                        DatabaseCuisine(name = "Jewish"),
                        DatabaseCuisine(name = "Korean"),
                        DatabaseCuisine(name = "Latin American")
                    )

                    addDataToDatabase(getUsersDatabase(application).usersDao, diets, cuisines)

                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                }
            }
        ).build()
    }

    return INSTANCE

}

private fun addDataToDatabase(usersDao: UsersDao, diets: Array<DatabaseDiet>, cuisines: Array<DatabaseCuisine>) {
    val ioScope = CoroutineScope(Dispatchers.IO)

    ioScope.launch {
        usersDao.addCuisine(*cuisines)
        usersDao.addDiet(*diets)
    }

}