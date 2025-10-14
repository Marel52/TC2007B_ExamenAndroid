package com.app.tc2007b_examenandroid.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.tc2007b_examenandroid.presentation.screens.home.components.CountryListTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCountryClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    var showSelfExplainedHook by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.refreshLastVisited()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Países del Mundo") },
                actions = {
                    IconButton(onClick = { showSelfExplainedHook = true }) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                label = { Text("Buscar país") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true
            )

            // Lista de países con todos los estados
            CountryListTab(
                countryList = uiState.filteredCountries,
                lastVisitedCountry = uiState.lastVisitedCountry,
                isLoading = uiState.isLoading,
                error = uiState.error,
                onCountryClick = onCountryClick,
                onRetry = { viewModel.retry() }
            )
        }
    }


    if (showSelfExplainedHook) {
        SelfExplainedHook(onDismiss = { showSelfExplainedHook = false })
    }
}

@Composable
fun SelfExplainedHook(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Self-explained hook") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Arquitectura: MVVM",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "La app sigue el patrón MVVM vista durante el curso, " +
                            "separando en capas: Data, Domain, DI y Presentation. " +
                            "La interfaz de dominio principal es CountryRepository, ",
                    style = MaterialTheme.typography.bodyMedium
                )

                Divider()

                Text(
                    text = "Estrategia de guardado de preferencias",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Se utiliza SharedPreferences para guardar el último país " +
                            "visitado de forma persistente entre sesiones.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Divider()

                Text(
                    text = "Estrategia de búsqueda",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "La búsqueda filtra la lista de países en tiempo real " +
                            "comparando el texto ingresado con los nombres comunes y " +
                            "oficiales de cada país, sin distinción de mayúsculas.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}