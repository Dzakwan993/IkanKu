package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.TopBarLogin
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") } // State untuk menyimpan input nomor ponsel
    val isPhoneNumberValid = phoneNumber.length == 12 // Simple validation for phone number length

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
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),

            ) {
                Spacer(modifier = Modifier.height(48.dp))

                Text(
                    text = "Masukkan Nomor Ponsel",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2151),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Input nomor ponsel
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = { Text("Nomor Ponsel Ex 6282387436427", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Keterangan
                Text(
                    text = "* Anda akan menerima SMS berisi kode daftar.",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                // Kebijakan Privasi
                Text(
                    text = buildAnnotatedString {
                        append("Dengan mengklik tombol ")
                        append("\"Lanjut\", Anda\nmenyetujui ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Kebijakan Privasi")
                        }
                        append(" dan ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Ketentuan Penggunaan.")
                        }
                    },
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)

                )


                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Lanjut
                TombolMasukkanKeranjang(
                    onClick = {
                        if (phoneNumber.isNotEmpty() && isPhoneNumberValid) {
                            // Navigate to the next screen if phone number is valid
                            navController.navigate("daftar_otp")
                        } else {
                            // Show error message
                        }
                    },
                    text = "Lanjut",
                    modifier = Modifier
                        .fillMaxWidth()

                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController() // Use rememberNavController for preview
    RegisterScreen(navController = navController)
}

