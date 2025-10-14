package com.app.tc2007b_examenandroid.presentation.screens.detail

import com.app.tc2007b_examenandroid.domain.model.Country

data class DetailUiState(
    val country: Country? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)