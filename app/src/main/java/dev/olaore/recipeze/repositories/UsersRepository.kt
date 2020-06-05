package dev.olaore.recipeze.repositories

import androidx.lifecycle.LiveData
import dev.olaore.recipeze.database.UsersDatabase
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.domain.User
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

}