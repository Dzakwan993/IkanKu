package com.example.ikanku.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.ui.components.CustomTopAppBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyEmailPenjualScreen() {
    var verificationCode by remember { mutableStateOf(List(6) { "" }) }
    var remainingTime by remember { mutableStateOf(60) }

    // Countdown logic
    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime -= 1
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Ubah Email",
                onBackClick = { /* Handle back navigation */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Silakan masukkan kode 6 digit yang dikirimkan melalui email", // Ensure this is a String
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center, // Center-align the text
                    modifier = Modifier.fillMaxWidth() // Make the Text take up full width of the column
                )




            Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Fharaa@gmail.com",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center, // Center-align the text
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Code input boxes
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in verificationCode.indices) {
                        OutlinedTextField(
                            value = verificationCode[i],
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) {
                                    verificationCode = verificationCode.toMutableList().apply { set(i, newValue) }
                                }
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.White, RoundedCornerShape(8.dp)),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Resend timer
                Text(
                    text = "Mohon tunggu ${remainingTime} detik untuk mengirim ulang",
                    fontSize = 12.sp,
                    color = if (remainingTime > 0) Color.Black else Color.Red,
                    textAlign = TextAlign.Center, // Center-align the text
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(
                onClick = { /* Handle next action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Selanjutnya", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyEmailPenjualScreenPreview() {
    VerifyEmailPenjualScreen()
}
