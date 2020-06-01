package dev.olaore.recipeze.repositories

import androidx.lifecycle.LiveData
import dev.olaore.recipeze.database.UsersDatabase
import dev.olaore.recipeze.models.database.DatabaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

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

}