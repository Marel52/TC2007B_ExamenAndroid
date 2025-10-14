package com.app.tc2007b_examenandroid.presentation.screens.home

import com.app.tc2007b_examenandroid.domain.model.Country

data class HomeUiState(
    val countryList: List<Country> = emptyList(),
    val filteredCountries: List<Country> = emptyList(),
    val lastVisitedCountry: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)