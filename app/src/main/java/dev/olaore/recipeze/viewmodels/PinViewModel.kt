package dev.olaore.recipeze.viewmodels

import androidx.lifecycle.ViewModel
import dev.olaore.recipeze.repositories.UsersRepository

class PinViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    val pin = usersRepository.user.value?.pin

}