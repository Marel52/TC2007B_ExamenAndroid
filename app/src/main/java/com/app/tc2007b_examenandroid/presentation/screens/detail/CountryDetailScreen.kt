package com.app.tc2007b_examenandroid.presentation.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.app.tc2007b_examenandroid.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del País") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {

                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }


                uiState.error != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = uiState.error ?: "Error desconocido",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.retry() }) {
                            Text("Reintentar")
                        }
                    }
                }

                uiState.country != null -> {
                    CountryDetailContent(country = uiState.country!!)
                }
            }
        }
    }
}

//Cargar información disponible del país
@Composable
fun CountryDetailContent(country: Country) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Bandera
        country.flags?.png?.let { flagUrl ->
            AsyncImage(
                model = flagUrl,
                contentDescription = "Bandera de ${country.name.common}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Nombre
        Text(
            text = country.name.common,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = country.name.official,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Divider()

        // Información básica (solo mostrar si existe)
        country.capital?.firstOrNull()?.let {
            DetailRow(label = "Capital", value = it)
        }

        country.region?.let {
            DetailRow(label = "Región", value = it)
        }

        country.subregion?.let {
            DetailRow(label = "Subregión", value = it)
        }

        country.population?.let {
            DetailRow(label = "Población", value = it.toString())
        }

        country.area?.let {
            DetailRow(label = "Área", value = "$it km²")
        }

        // Idiomas
        country.languages?.let { languages ->
            if (languages.isNotEmpty()) {
                DetailRow(
                    label = "Idiomas",
                    value = languages.values.joinToString(", ")
                )
            }
        }

        // Monedas
        country.currencies?.let { currencies ->
            if (currencies.isNotEmpty()) {
                Text(
                    text = "Monedas:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                currencies.forEach { (code, currency) ->
                    Text(
                        text = "${currency.name} (${currency.symbol ?: code})",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Zonas horarias
        country.timezones?.let { timezones ->
            if (timezones.isNotEmpty()) {
                DetailRow(
                    label = "Zonas horarias",
                    value = timezones.joinToString(", ")
                )
            }
        }

        // Continentes
        country.continents?.let { continents ->
            if (continents.isNotEmpty()) {
                DetailRow(
                    label = "Continentes",
                    value = continents.joinToString(", ")
                )
            }
        }

        // Escudo
        country.coatOfArms?.png?.let { coatUrl ->
            Text(
                text = "Escudo de Armas:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = coatUrl,
                contentDescription = "Escudo de ${country.name.common}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f)
        )
    }
}