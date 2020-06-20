package dev.olaore.recipeze.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import dev.olaore.recipeze.database.UsersDatabase
import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.models.mappers.asDomainModel
import dev.olaore.recipeze.models.mappers.asPreferenceCuisineDomainModel
import dev.olaore.recipeze.models.mappers.asPreferenceDietDomainModel
import kotlinx.coroutines.*

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

    suspend fun getUser(): LiveData<DatabaseUser> {
        return withContext(ioScope.coroutineContext) {
            usersDao.getUser()
        }
    }

    suspend fun registerUser(user: User) {
        ioScope.launch {
            usersDao.addUser(
                DatabaseUser(username = user.username!!, pin = user.pin!!, diets = user.diets, cuisines = user.cuisines)
            )
        }
    }

}