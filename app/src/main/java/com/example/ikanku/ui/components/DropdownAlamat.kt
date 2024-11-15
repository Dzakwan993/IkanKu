package com.example.ikanku.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CascadingDropdown(
    onCitySelected: (String) -> Unit,
    onDistrictSelected: (String) -> Unit,
    onPostalCodeSelected: (String) -> Unit
) {
    // Sample data for cities, districts, and postal codes
    val cities = listOf("Kota Batam")
    val districts = mapOf(
        "Kota Batam" to listOf("Sekupang", "Batam Kota", "Batu Aji", "Batu Ampar", "Belakang Padang")
    )
    val postalCodes = mapOf(
        "Sekupang" to "29425",
        "Batam Kota" to "29445",
        "Batu Aji" to "29446",
        "Batu Ampar" to "29447",
        "Belakang Padang" to "29448"
    )

    // States for selected values and dropdown visibility
    var selectedCity by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf("") }
    var selectedPostalCode by remember { mutableStateOf("") }
    var cityDropdownExpanded by remember { mutableStateOf(false) }
    var districtDropdownExpanded by remember { mutableStateOf(false) }
    var postalCodeDropdownExpanded by remember { mutableStateOf(false) }

    // Dropdown for City
    Column {
        OutlinedTextField(
            value = selectedCity,
            onValueChange = {},
            readOnly = true,
            label = { Text("Kota") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { cityDropdownExpanded = true },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityDropdownExpanded) }
        )
        DropdownMenu(
            expanded = cityDropdownExpanded,
            onDismissRequest = { cityDropdownExpanded = false }
        ) {
            cities.forEach { city ->
                DropdownMenuItem(
                    text = { Text(city) },
                    onClick = {
                        selectedCity = city
                        selectedDistrict = ""
                        selectedPostalCode = ""
                        onCitySelected(city)
                        cityDropdownExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dropdown for District
        if (selectedCity.isNotEmpty()) {
            OutlinedTextField(
                value = selectedDistrict,
                onValueChange = {},
                readOnly = true,
                label = { Text("Kecamatan") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { districtDropdownExpanded = true },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = districtDropdownExpanded) }
            )
            DropdownMenu(
                expanded = districtDropdownExpanded,
                onDismissRequest = { districtDropdownExpanded = false }
            ) {
                districts[selectedCity]?.forEach { district ->
                    DropdownMenuItem(
                        text = { Text(district) },
                        onClick = {
                            selectedDistrict = district
                            selectedPostalCode = ""
                            onDistrictSelected(district)
                            districtDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dropdown for Postal Code
        if (selectedDistrict.isNotEmpty()) {
            OutlinedTextField(
                value = selectedPostalCode,
                onValueChange = {},
                readOnly = true,
                label = { Text("Kode Pos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { postalCodeDropdownExpanded = true },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = postalCodeDropdownExpanded) }
            )
            DropdownMenu(
                expanded = postalCodeDropdownExpanded,
                onDismissRequest = { postalCodeDropdownExpanded = false }
            ) {
                val postalCode = postalCodes[selectedDistrict]
                DropdownMenuItem(
                    text = { Text(postalCode ?: "") },
                    onClick = {
                        selectedPostalCode = postalCode ?: ""
                        onPostalCodeSelected(postalCode ?: "")
                        postalCodeDropdownExpanded = false
                    }
                )
            }
        }
    }
}
