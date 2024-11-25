package com.example.ikanku.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.TopBarLogin
import androidx.compose.material3.ModalBottomSheet as ModalBottomSheet1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteDataScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var showDistrictPicker by remember { mutableStateOf(false) }
    var showPostalCodePicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarLogin(
                selectedTab = "Daftar",
                onTabSelected = { /* Handle tab selection */ },
                navController = navController
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Lengkapi Data",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2151)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Input fields
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = { Text("Nama lengkap", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // District Selection Field
                OutlinedTextField(
                    value = selectedDistrict,
                    onValueChange = { },
                    placeholder = { Text("Pilih Kecamatan", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDistrictPicker = true },
                    shape = RoundedCornerShape(16.dp),
                    colors = outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.down),
                            contentDescription = "Dropdown Icon",
                            tint = Color.Gray,
                            modifier = Modifier.clickable { showDistrictPicker = true }
                        )
                    },
                    readOnly = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Postal Code Selection Field
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = { },
                    placeholder = { Text("Pilih Kode Pos", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { if (selectedDistrict.isNotEmpty()) showPostalCodePicker = true },
                    shape = RoundedCornerShape(16.dp),
                    colors = outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.down),
                            contentDescription = "Dropdown Icon",
                            tint = Color.Gray,
                            modifier = Modifier.clickable {
                                if (selectedDistrict.isNotEmpty()) showPostalCodePicker = true
                            }
                        )
                    },
                    readOnly = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text("Nama Jalan, Gedung, No. Rumah", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }

            // Button at the bottom
            Button(
                onClick = {navController.navigate("beranda_screen")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Daftar", color = Color.White, fontSize = 16.sp)
            }
        }
    }

    // ModalBottomSheet for District Selection
    if (showDistrictPicker) {
        ModalBottomSheet1(
            onDismissRequest = { showDistrictPicker = false }
        ) {
            CitySelectionBottomSheet(
                items = listOf("Batu Aji", "Batam Kota", "Sekupang"),
                onItemSelected = { district ->
                    selectedDistrict = district
                    showDistrictPicker = false
                    showPostalCodePicker = true
                }
            )
        }
    }

    // ModalBottomSheet for Postal Code Selection
    if (showPostalCodePicker) {
        ModalBottomSheet1(
            onDismissRequest = { showPostalCodePicker = false }
        ) {
            CitySelectionBottomSheet(
                items = when (selectedDistrict) {
                    "Batu Aji" -> listOf("29422", "29423")
                    "Batam Kota" -> listOf("29444", "29445")
                    "Sekupang" -> listOf("29445", "29446")
                    else -> emptyList()
                },
                onItemSelected = { code ->
                    postalCode = code
                    showPostalCodePicker = false
                }
            )
        }
    }
}

@Composable
fun CitySelectionBottomSheet(
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items.forEach { item ->
            TextButton(
                onClick = { onItemSelected(item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = item,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CompleteDataScreenPreview() {
    val navController = rememberNavController()
    CompleteDataScreen(navController = navController)
}
