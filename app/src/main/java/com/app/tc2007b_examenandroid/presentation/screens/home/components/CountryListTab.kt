package com.app.tc2007b_examenandroid.presentation.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.tc2007b_examenandroid.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListTab(
    countryList: List<Country>,
    lastVisitedCountry: String?,
    isLoading: Boolean,
    error: String?,
    onCountryClick: (String) -> Unit,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            // Manejo de estado cargando
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Error con retry
            error != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = error,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRetry) {
                        Text("Reintentar")
                    }
                }
            }

            // Lista vacía
            countryList.isEmpty() -> {
                Text(
                    text = "No se encontraron países",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Success con Grid
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(countryList) { country ->
                        CountryCard(
                            country = country,
                            isLastVisited = country.name.common.equals(
                                lastVisitedCountry,
                                ignoreCase = true
                            ),
                            onClick = { onCountryClick(country.name.common) }
                        )
                    }
                }
            }
        }
    }
}