package dev.olaore.recipeze

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.olaore.recipeze.viewmodels.ViewModelFactory

fun <T: ViewModel> Fragment.obtainViewModel(modelClass: Class<T>): T {

    val usersRepository = (requireActivity().application as RecipesApplication).usersRepository
    val recipesRepository = (requireActivity().application as RecipesApplication).recipesRepository
    val viewModelFactory = ViewModelFactory(usersRepository, recipesRepository)

    return ViewModelProvider(
        this, viewModelFactory
    ).get(modelClass)

}

fun <T: ViewModel> Fragment.obtainParentViewModel(modelClass: Class<T>): T {

    val usersRepository = (requireActivity().application as RecipesApplication).usersRepository
    val recipesRepository = (requireActivity().application as RecipesApplication).recipesRepository
    val viewModelFactory = ViewModelFactory(usersRepository, recipesRepository)

    return ViewModelProvider(
        requireActivity(), viewModelFactory
    ).get(modelClass)

}

fun <T: ViewModel> AppCompatActivity.obtainViewModel(modelClass: Class<T>): T {

    val usersRepository = (this.application as RecipesApplication).usersRepository
    val recipesRepository = (this.application as RecipesApplication).recipesRepository
    val viewModelFactory = ViewModelFactory(usersRepository, recipesRepository)

    return ViewModelProvider(
        this, viewModelFactory
    ).get(modelClass)

}