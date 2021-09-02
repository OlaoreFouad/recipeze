package dev.olaore.recipeze.listeners

interface OnRecentSearchInteraction {

    fun onDeleteSearch(id: String)

    fun onSelectSearch(query: String)

}