package dev.olaore.recipeze.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import dev.olaore.recipeze.database.UsersDatabase
import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseRecentSearch
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.RecentSearch
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.mappers.asDatabaseModel
import dev.olaore.recipeze.models.mappers.asDomainModel
import dev.olaore.recipeze.models.mappers.asPreferenceCuisineDomainModel
import dev.olaore.recipeze.models.mappers.asPreferenceDietDomainModel
import kotlinx.coroutines.*
import java.util.*

class UsersRepository(val database: UsersDatabase) {

    private val usersDao = database.usersDao
    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    val diets = Transformations.map(database.usersDao.getStoredDiets()) {
        it.asPreferenceDietDomainModel()
    }

    val cuisines = Transformations.map(database.usersDao.getStoredCuisines()) {
        it.asPreferenceCuisineDomainModel()
    }

    val user = Transformations.map(database.usersDao.getUser()) {
        it.asDomainModel()
    }

    val recentSearches = Transformations.map(database.recentSearchesDao.getRecentSearches()) {
        it.map { search -> search.asDomainModel() }
    }

    suspend fun getUser(): LiveData<DatabaseUser> {
        return withContext(ioScope.coroutineContext) {
            usersDao.getUser()
        }
    }

    fun deleteAllUsers() {
        ioScope.launch {
            usersDao.deleteAllUsers()
        }
    }

    fun registerUser(user: User) {
        Log.d("PreferencesViewModel", "$user")
        ioScope.launch {
            usersDao.addUser(
                user.asDatabaseModel()
            )
        }
    }

    fun saveRecentSearch(query: String) {
        ioScope.launch {
            database.recentSearchesDao.addRecentSearch(DatabaseRecentSearch(
                UUID.randomUUID().toString(), query, System.currentTimeMillis()
            ))
        }
    }

    fun deleteRecentSearch(id: String) {
        ioScope.launch {
            database.recentSearchesDao.deleteRecentSearch(id)
        }
    }

}