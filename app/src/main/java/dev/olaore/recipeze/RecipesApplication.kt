package dev.olaore.recipeze

import android.app.Application
import dev.olaore.recipeze.database.getDatabase
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.network.Network
import dev.olaore.recipeze.network.RecipesApiHelper
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository

class RecipesApplication : Application() {

    val usersRepository: UsersRepository
            get() =  UsersRepository(getUsersDatabase(this.applicationContext))
    val recipesRepository: RecipesRepository
            get() = RecipesRepository(
                getDatabase(this.applicationContext),
                RecipesApiHelper(Network.recipesService)
            )

}