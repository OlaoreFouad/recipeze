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

@Database(
    entities = [DatabaseUser::class, DatabaseDiet::class, DatabaseCuisine::class, SearchResult::class],
    version = 1
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

                    // add logic to add the diets and cuisine data upon creation

                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                }
            }
        ).build()
    }

    return INSTANCE

}