package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.repositories.UsersRepository
import dev.olaore.recipeze.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreferencesViewModel(private val app: Application) : AndroidViewModel(app) {

    private var usersRepository = UsersRepository(getUsersDatabase(app))

    private var _diets = usersRepository.diets as MutableLiveData
    val diets: LiveData<List<Preference>>
        get() = _diets

    private var _cuisines = usersRepository.cuisines
    val cuisines: LiveData<List<Preference>>
        get() = _cuisines

    private var _allSelected = MutableLiveData(false)
    val allSelected: LiveData<Boolean>
        get() = _allSelected

    private var _currentPreference = MutableLiveData(Constants.DIETS_PREFERENCE)
    val currentPreference: LiveData<String>
        get() = _currentPreference

    init {
    }

    fun onPreferenceSelected(position: Int) {
        val currentPref = currentPreference.value
        if (currentPref == Constants.DIETS_PREFERENCE) {
            val currentValue = _diets.value
            val pref = currentValue!![position]

            pref.isSelected = !pref.isSelected

            _diets.value = currentValue

        } else {
            _cuisines.value!![position].isSelected = !_cuisines.value!![position].isSelected
        }
    }


}