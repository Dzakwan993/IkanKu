package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UbahNomorPonselScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Ganti Nomor Ponsel",
                onBackClick = { navController.popBackStack()}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding( vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth() .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Nomor Ponsel Baru",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start
                )


                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },

                    placeholder = { Text("Contoh: 6282387436427") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Informasi teks di tengah
                Text(
                    text = "*Anda akan menerima SMS berisi kode daftar.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            // Tombol di bagian bawah
            TombolMasukkanKeranjang(
                text = "Selanjutnya",
                onClick = {
                    navController.navigate("ubah_nomor_otp")
                }
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun UbahNomorPonselScreenPreview() {
    val navController = rememberNavController()
    UbahNomorPonselScreen(navController = navController)
}
