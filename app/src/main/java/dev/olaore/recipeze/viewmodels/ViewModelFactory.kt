@file:Suppress("UNCHECKED_CAST")

package dev.olaore.recipeze.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.olaore.recipeze.repositories.RecipesRepository
import dev.olaore.recipeze.repositories.UsersRepository
import java.lang.IllegalArgumentException

@Suppress("IMPLICIT_CAST_TO_ANY")
class ViewModelFactory(
    private val usersRepository: UsersRepository,
    private val recipesRepository: RecipesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(usersRepository, recipesRepository)
            PreferencesViewModel::class.java -> PreferencesViewModel(usersRepository)
            PinViewModel::class.java -> PinViewModel(usersRepository)
            else -> {
                IllegalArgumentException("ViewModel class must be part of recognized viewmodels for the factory")
            }
        } as T
    }

}