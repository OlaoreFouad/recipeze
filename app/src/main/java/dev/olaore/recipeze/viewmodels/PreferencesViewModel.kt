package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.repositories.UsersRepository
import dev.olaore.recipeze.utils.Constants
import kotlinx.coroutines.launch

class PreferencesViewModel(private val app: Application) : AndroidViewModel(app) {

    private var _diets = MutableLiveData<List<Preference>>()
    val diets: LiveData<List<Preference>>
        get() = _diets

    private var _cuisines = MutableLiveData<List<Preference>>()
    val cuisines: LiveData<List<Preference>>
        get() = _cuisines

    private var _allSelected = MutableLiveData(false)
    val allSelected: LiveData<Boolean>
        get() = _allSelected

    private var _currentPreference = MutableLiveData(Constants.DIETS_PREFERENCE)
    val currentPreference: LiveData<String>
        get() = _currentPreference

    private var usersRepository = UsersRepository(getUsersDatabase(app))

    init {
        getStoredDiets()
        getStoredCuisines()
    }

    private fun getStoredDiets() {
        viewModelScope.launch {
            _diets = usersRepository.getStoredDiets() as MutableLiveData<List<Preference>>
        }
    }

    private fun getStoredCuisines() {
        viewModelScope.launch {
            _cuisines = usersRepository.getStoredCuisines() as MutableLiveData<List<Preference>>
        }
    }

}