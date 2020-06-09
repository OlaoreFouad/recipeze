package dev.olaore.recipeze.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.olaore.recipeze.database.UsersDatabase
import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.mappers.asPreferenceCuisineDomainModel
import dev.olaore.recipeze.models.mappers.asPreferenceDietDomainModel
import kotlinx.coroutines.*

class UsersRepository(val database: UsersDatabase) {

    private val usersDao = database.usersDao
    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    suspend fun getUser(username: String, pin: String): LiveData<DatabaseUser?> {
        return withContext(ioScope.coroutineContext) {
            usersDao.getUser(
                username,
                pin
            )
        }
    }

    suspend fun registerUser(user: User) {
        ioScope.launch {
            usersDao.addUser(
                DatabaseUser(username = user.username!!, pin = user.pin!!, diets = user.diets, cuisines = user.cuisines)
            )
        }
    }

    suspend fun getStoredDiets(): LiveData<List<Preference>> {
        val diets = withContext(ioScope.coroutineContext) { usersDao.getStoredDiets() }

        return Transformations.map(diets) { databaseDiets ->
            databaseDiets.asPreferenceDietDomainModel()
        }
    }

    suspend fun getStoredCuisines(): LiveData<List<Preference>> {
        val cuisines = withContext(ioScope.coroutineContext) { usersDao.getStoredCuisines() }

        return Transformations.map(cuisines) { databaseCuisines ->
            databaseCuisines.asPreferenceCuisineDomainModel()
        }
    }

}