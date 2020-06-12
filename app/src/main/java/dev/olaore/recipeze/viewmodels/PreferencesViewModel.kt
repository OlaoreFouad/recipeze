package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.repositories.UsersRepository
import dev.olaore.recipeze.utils.Constants
import kotlinx.coroutines.Dispatchers
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
//        getStoredDiets()
//        getStoredCuisines()

//        getStoredDietsLive()

    }

    private fun getStoredDiets() {
        viewModelScope.launch {
            val databaseDiets =  usersRepository.getStoredDiets()
            Log.d("PreferencesFragment", "Database Diets Size: ${ databaseDiets.size }")
        }
    }

    fun getStoredDietsLive() : LiveData<List<Preference>>? {
        var livediets: LiveData<List<Preference>>? = null
        viewModelScope.launch {
            livediets = usersRepository.getStoredDietsLive()
        }

        return livediets
    }

    private fun getStoredCuisines() {
        viewModelScope.launch {
            _cuisines.value = usersRepository.getStoredCuisines().value
        }
    }

}