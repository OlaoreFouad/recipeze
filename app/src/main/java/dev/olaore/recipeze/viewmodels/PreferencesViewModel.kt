package dev.olaore.recipeze.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dev.olaore.recipeze.database.getUsersDatabase
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.User
import dev.olaore.recipeze.repositories.UsersRepository
import dev.olaore.recipeze.utils.Constants
import dev.olaore.recipeze.utils.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private var savedDiets = ""
    private var savedCuisines = ""

    var user = User()

    private var _diets = usersRepository.diets as MutableLiveData
    val diets: LiveData<List<Preference>>
        get() = _diets

    private var _cuisines = usersRepository.cuisines as MutableLiveData
    val cuisines: LiveData<List<Preference>>
        get() = _cuisines

    private var _allSelected = MutableLiveData(false)
    val allSelected: LiveData<Boolean>
        get() = _allSelected

    private var _currentPreference = MutableLiveData(Constants.CUISINES_PREFERENCE)
    val currentPreference: LiveData<String>
        get() = _currentPreference

    private var _registrationStatus = MutableLiveData<Boolean>(false)
    val registrationStatus: LiveData<Boolean>
        get() = _registrationStatus

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
        if (currentPreference.value == Constants.CUISINES_PREFERENCE) {
            savedCuisines = _cuisines.value?.filter { it.isSelected }?.joinToString(
                separator = ","
            ) { it.name }!!

            _currentPreference.value = Constants.DIETS_PREFERENCE
            _allSelected.value = false

        } else if (currentPreference.value == Constants.DIETS_PREFERENCE) {
            savedDiets = _diets.value?.filter { it.isSelected }?.joinToString(
                separator = ","
            ) { it.name }!!

            registerUser()
        }
    }

    fun patchUser(user: User) {
        this.user.username = user.username
        this.user.pin = user.pin
    }

    private fun registerUser() {
        user.diets = savedDiets
        user.cuisines = savedCuisines

        // TODO: build pin screen

        viewModelScope.launch {
            usersRepository.registerUser(user)
            _registrationStatus.value = true
        }

    }


}