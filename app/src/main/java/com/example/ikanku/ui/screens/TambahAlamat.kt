package com.example.ikanku.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen() {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var streetAddress by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var locationAdjustment by remember { mutableStateOf("") }
    var showCityPicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Tambah alamat",
                onBackClick = { /* Handle back navigation */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Input Fields
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Nama Lengkap*") },
                placeholder = { Text("Masukkan Nama Lengkap") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Nomor Ponsel*") },
                placeholder = { Text("Masukkan Nomor Ponsel") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            OutlinedTextField(
                value = streetAddress,
                onValueChange = { streetAddress = it },
                label = { Text("Nama Jalan, Gedung, No. Rumah*") },
                placeholder = { Text("Masukkan Alamat Lengkap") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            OutlinedTextField(
                value = district,
                onValueChange = { },
                label = { Text("Kecamatan, kode pos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { showCityPicker = true },
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_kebawah),
                        contentDescription = "Dropdown Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable { showCityPicker = true }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                readOnly = true
            )

            OutlinedTextField(
                value = locationAdjustment,
                onValueChange = { },
                label = { Text("Atur Sesuaikan lokasi") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { showCityPicker = true },
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_kebawah),
                        contentDescription = "Dropdown Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable { showCityPicker = true }
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            // Set as Primary Address Button
            Button(
                onClick = { /* Handle set as primary address */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Atur sebagai alamat utama", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.refresh),
                    contentDescription = "Refresh Icon",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cancel and Save Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Padding bottom 16.dp added here
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Handle cancel action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Batal", color = Color.White)
                }

                Button(
                    onClick = { /* Handle save action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Simpan", color = Color.White)
                }
            }
        }
    }

    if (showCityPicker) {
        ModalBottomSheet(
            onDismissRequest = { showCityPicker = false }
        ) {
            CitySelectionAddresBottomSheet(
                onCitySelected = { selectedCity ->
                    district = selectedCity
                    showCityPicker = false
                }
            )
        }
    }
}

@Composable
fun CitySelectionAddresBottomSheet(
    onCitySelected: (String) -> Unit
) {
    val cities = listOf(
        "Batam - Kec. Batu Aji - 29422",
        "Batam - Kec. Batam Kota - 29444",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445",
        "Batam - Kec. Sekupang - 29445"
        // Add more cities as needed
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pilih Kota dan Kecamatan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        cities.forEach { city ->
            TextButton(
                onClick = { onCitySelected(city) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = city,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddAddressScreenPreview() {
    AddAddressScreen()
}
