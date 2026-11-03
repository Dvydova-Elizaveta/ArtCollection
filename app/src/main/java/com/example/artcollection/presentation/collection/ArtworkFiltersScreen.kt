package com.example.artcollection.presentation.collection


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artcollection.domain.model.ArtworkFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkFiltersScreen(
    onApplyFilters: (ArtworkFilter) -> Unit,
    onClearFilters: () -> Unit,
    onBack: () -> Unit
) {
    var artist by rememberSaveable { mutableStateOf("") }
    var title by rememberSaveable { mutableStateOf("") }
    var artworkType by rememberSaveable { mutableStateOf<String?>(null) }

    var expanded by remember { mutableStateOf(false) }

    val artworkTypes = listOf(
        "Painting",
        "Sculpture",
        "Print",
        "Photograph",
        "Drawing",
        "Textile",
        "Ceramics"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Фильтры") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Назад")
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = artist,
                onValueChange = { artist = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Художник") },
                placeholder = { Text("Введите имя художника") },
                singleLine = true
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Название произведения") },
                placeholder = { Text("Введите название") },
                singleLine = true
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = artworkType ?: "",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text("Тип произведения") },
                    placeholder = { Text("Выберите тип") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    artworkTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                artworkType = type
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    onApplyFilters(
                        ArtworkFilter(
                            artist = artist.trim(),
                            title = title.trim(),
                            artworkType = artworkType
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Применить фильтры")
            }

            TextButton(
                onClick = {
                    artist = ""
                    title = ""
                    artworkType = null
                    onClearFilters()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Сбросить")
            }
        }
    }
}
