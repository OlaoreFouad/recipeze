package dev.olaore.recipeze.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.repositories.UsersRepository

class PinViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    var pin = ""
    var providedPin = ""

    private var _user = usersRepository.user
    val user: LiveData<User>
        get() = _user

    fun isEqual() = pin == providedPin

}