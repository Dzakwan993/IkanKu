package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun EditTokoScreen(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    navController: NavController
) {
    // Create state variables for each TextField
    val storeName = remember { mutableStateOf(TextFieldValue()) }
    val ownerName = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
    val operatingHours = remember { mutableStateOf(TextFieldValue()) }
    val deliveryDescription = remember { mutableStateOf(TextFieldValue()) }
    val storeAddress = remember { mutableStateOf(TextFieldValue()) }
    val storeDescription = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Edit Toko",
                onBackClick = {navController.popBackStack()}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Foto Profil
            Box(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tibelat_farm),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.LightGray, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Input Cards Terpisah
            InputCard(label = "Nama Toko", placeholder = "Masukkan nama toko", value = storeName.value, onValueChange = { storeName.value = it })
            InputCard(label = "Nama Anda", placeholder = "Masukkan nama Anda", value = ownerName.value, onValueChange = { ownerName.value = it })
            InputCard(label = "Nomor Ponsel", placeholder = "Masukkan nomor ponsel", value = phoneNumber.value, onValueChange = { phoneNumber.value = it })
            InputCard(label = "Waktu Operasional", placeholder = "Masukkan waktu operasional", value = operatingHours.value, onValueChange = { operatingHours.value = it })
            InputCard(label = "Deskripsi Pengiriman", placeholder = "Masukkan deskripsi pengiriman", value = deliveryDescription.value, onValueChange = { deliveryDescription.value = it })
            InputCard(label = "Alamat Toko", placeholder = "Masukkan alamat toko", value = storeAddress.value, onValueChange = { storeAddress.value = it })
            InputCard(label = "Deskripsi Toko", placeholder = "Masukkan deskripsi toko", value = storeDescription.value, onValueChange = { storeDescription.value = it })

            // Tombol Logout
            Button(
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(24.dp)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Red
                )
            ) {
                Text(text = "Logout")
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Batal dan Simpan
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .width(160.dp)
                        .height(56.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238))
                ) {
                    Text(text = "Batal", color = Color.White)
                }

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .width(160.dp)
                        .height(56.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD))
                ) {
                    Text(text = "Simpan", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCard(
    label: String,
    placeholder: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                color = Color.Gray
            )
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(placeholder) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditTokoScreen() {
    EditTokoScreen(
        onBackClick = { /* Handle back navigation */ },
        onLogoutClick = { /* Handle logout */ },
        onSaveClick = { /* Handle save */ },
        onCancelClick = { /* Handle cancel */ },
        navController = rememberNavController()
    )
}

