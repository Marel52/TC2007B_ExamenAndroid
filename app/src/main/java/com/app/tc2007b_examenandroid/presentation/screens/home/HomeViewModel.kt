package com.app.tc2007b_examenandroid.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tc2007b_examenandroid.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadCountries()
        loadLastVisitedCountry()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.getAllCountries()
                .onSuccess { countries ->
                    countries.take(3).forEach { country ->
                        println("🏳️ ${country.name.common}: flags=${country.flags}")
                    }

                    _uiState.update {
                        it.copy(
                            countryList = countries,
                            filteredCountries = countries,
                            isLoading = false
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Error al cargar países: ${exception.message}"
                        )
                    }
                }
        }
    }

    private fun loadLastVisitedCountry() {
        val lastVisited = repository.getLastVisitedCountry()
        _uiState.update { it.copy(lastVisitedCountry = lastVisited) }
    }

    fun refreshLastVisited() {
        loadLastVisitedCountry()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query

        val filtered = if (query.isBlank()) {
            _uiState.value.countryList
        } else {
            _uiState.value.countryList.filter { country ->
                country.name.common.contains(query, ignoreCase = true) ||
                        country.name.official.contains(query, ignoreCase = true)
            }
        }

        _uiState.update { it.copy(filteredCountries = filtered) }
    }

    fun retry() {
        loadCountries()
    }
}