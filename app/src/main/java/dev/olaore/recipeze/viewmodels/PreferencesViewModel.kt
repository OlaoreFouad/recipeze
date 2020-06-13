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
    private var savedDiets = ""
    private var savedCuisines = ""

    private var _diets = usersRepository.diets as MutableLiveData
    val diets: LiveData<List<Preference>>
        get() = _diets

    private var _cuisines = usersRepository.cuisines as MutableLiveData
    val cuisines: LiveData<List<Preference>>
        get() = _cuisines

    private var _allSelected = MutableLiveData(false)
    val allSelected: LiveData<Boolean>
        get() = _allSelected

    private var _currentPreference = MutableLiveData(Constants.DIETS_PREFERENCE)
    val currentPreference: LiveData<String>
        get() = _currentPreference

    private var selectedPrefs = listOf<Preference>()

    init {
    }

    fun onPreferenceSelected(position: Int) {
        val currentPref = currentPreference.value
        if (currentPref == Constants.DIETS_PREFERENCE) {
            val currentValue = _diets.value
            val pref = currentValue!![position]

            pref.isSelected = !pref.isSelected

            selectedPrefs = currentValue.filter { it.isSelected }
            _allSelected.value = currentValue.size == selectedPrefs.size

            _diets.value = currentValue

        } else {
            val currentValue = _cuisines.value
            val pref = currentValue!![position]

            pref.isSelected = !pref.isSelected

            selectedPrefs = currentValue.filter { it.isSelected }
            _allSelected.value = currentValue.size == selectedPrefs.size

            _cuisines.value = currentValue
        }
    }

    fun onAllPreferencesSelected() {
        val currentPref = currentPreference.value
        val allSelected = allSelected.value
        if (currentPref == Constants.DIETS_PREFERENCE) {
            val currentDiets = _diets.value
            currentDiets!!.forEach { it.isSelected = !allSelected!! }
            _diets.value = currentDiets
        } else {
            val currentCuisines = _cuisines.value
            currentCuisines!!.forEach { it.isSelected = !allSelected!! }
            _cuisines.value = currentCuisines
        }

        _allSelected.value = !allSelected!!
    }

    fun onSaveOrFinish() {
        if (currentPreference.value == Constants.DIETS_PREFERENCE) {
            savedDiets = _diets.value?.joinToString(
                separator = ","
            ) { it.name }!!

            _currentPreference.value = Constants.CUISINES_PREFERENCE
            _allSelected.value = false
        } else if (currentPreference.value == Constants.CUISINES_PREFERENCE) {
            savedCuisines = _cuisines.value?.joinToString(
                separator = ","
            ) { it.name }!!
        }
    }


}