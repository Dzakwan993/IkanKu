package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import android.widget.Toast

import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ikanku.model.Kecamatan
import com.example.ikanku.model.KodePos
import com.example.ikanku.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.ikanku.R
import com.example.ikanku.ui.components.TopBarLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteDataScreen(navController: NavController, viewModel: RegisterViewModel) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf("") }
    var districtId by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var postalCodeId by remember { mutableStateOf("") }
    var showDistrictPicker by remember { mutableStateOf(false) }
    var showPostalCodePicker by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }

    val isLoading = viewModel.isLoading.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value
    val context = LocalContext.current // Context untuk menampilkan Toast


    // Fetch districts when the modal is opened
    LaunchedEffect(showDistrictPicker) {
        if (showDistrictPicker) {
            viewModel.fetchKecamatan()  // Fetch kecamatan when bottom sheet is shown
        }
    }

    LaunchedEffect(districtId) {
        if (districtId.isNotEmpty()) {
            viewModel.fetchPostalCodes(districtId)  // Fetch postal codes based on selected district
        }
    }

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
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),

                horizontalAlignment = Alignment.Start
            ) {
                // Full Name Field
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Nama Lengkap") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = outlinedTextFieldColors(

                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },

                    placeholder = { Text("Email", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
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
                    shape = RoundedCornerShape(10.dp),
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
                    shape = RoundedCornerShape(10.dp),
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
                    shape = RoundedCornerShape(10.dp),
                    colors = outlinedTextFieldColors(

                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                // District Picker
                OutlinedTextField(
                    value = selectedDistrict,
                    onValueChange = {},
                    label = { Text("Kecamatan") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable {
                            showDistrictPicker = true
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    readOnly = true
                )

                // Postal Code Picker
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = {},
                    label = { Text("Kode Pos") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable {
                            showPostalCodePicker = true
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    readOnly = true
                )

                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Kata Sandi") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            // Button at the bottom
            TombolMasukkanKeranjang(
                onClick = { if (fullName.isEmpty() || email.isEmpty() || address.isEmpty() || selectedDistrict.isEmpty() || postalCode.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Semua field wajib diisi", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.registerUser(
                        fullName = fullName,
                        email = email,
                        address = address,
                        districtId = districtId,
                        postalCodeId = postalCodeId,
                        password = password,
                        onSuccess = {
                            Toast.makeText(context, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                            navController.navigate("beranda_screen")
                        },
                        onError = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                } },
                text = "Daftar",
                modifier = Modifier
                    .fillMaxWidth()

            )


        }
    }

    // ModalBottomSheet for District Selection
    if (showDistrictPicker) {
        ModalBottomSheet(onDismissRequest = { showDistrictPicker = false }) {
            DistrictSelectionBottomSheet(
                districts = viewModel.kecamatanList.collectAsState().value,  // Mengambil data kecamatan dari viewModel
                onItemClick = { district ->
                    selectedDistrict = district.nama_kecamatan
                    districtId = district.id_kecamatan.toString()
                    showDistrictPicker = false
                }
            )
        }
    }

    // ModalBottomSheet for Postal Code Selection
    if (showPostalCodePicker) {
        ModalBottomSheet(onDismissRequest = { showPostalCodePicker = false }) {
            PostalCodeSelectionBottomSheet(
                postalCodes = viewModel.kodePosList.collectAsState().value,  // Mengambil data kode pos dari viewModel
                onItemClick = { postal ->
                    postalCode = postal.kode_pos
                    postalCodeId = postal.id_kode_pos.toString()
                    showPostalCodePicker = false
                }
            )
        }
    }
}

@Composable
fun DistrictSelectionBottomSheet(districts: List<Kecamatan>, onItemClick: (Kecamatan) -> Unit) {
    LazyColumn {
        items(districts) { district ->
            Text(
                text = district.nama_kecamatan,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onItemClick(district) },
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun PostalCodeSelectionBottomSheet(postalCodes: List<KodePos>, onItemClick: (KodePos) -> Unit) {
    LazyColumn {
        items(postalCodes) { postal ->
            Text(
                text = postal.kode_pos,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onItemClick(postal) },
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}
