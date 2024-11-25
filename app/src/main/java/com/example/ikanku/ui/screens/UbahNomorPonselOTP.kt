package com.example.ikanku.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(navController: NavController) {
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
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Box container untuk isi halaman
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Silakan masukkan kode 6 digit yang dikirimkan melalui SMS ke",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "6285274088734",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Input untuk kode verifikasi
                    OTPInputBox()

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Mohon tunggu 60 detik untuk mengirim ulang",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Lanjut
            Button(
                onClick = { /* Handle lanjut action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Lanjut", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun OTPInputBox() {
    var otpCode = remember { mutableStateOf(List(6) { "" }) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in otpCode.value.indices) {
            OTPTextField(
                value = otpCode.value[index],
                onValueChange = { newDigit ->
                    if (newDigit.length <= 1 && newDigit.all { it.isDigit() }) {
                        otpCode.value = otpCode.value.toMutableList().apply { this[index] = newDigit }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        modifier = Modifier
            .size(48.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    val navController = rememberNavController()
    VerificationScreen(navController = navController)
}
