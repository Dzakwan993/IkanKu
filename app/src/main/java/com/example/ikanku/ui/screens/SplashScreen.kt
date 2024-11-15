package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ikanku.R

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF177BCD)), // Warna latar biru sesuai gambar
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ikanku), // Ganti dengan nama file drawable untuk ikon ikan
                contentDescription = "Logo Ikan",
                modifier = Modifier.size(200.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewLDaftarScreen() {
    RegisterScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviwOTP() {
    ConfirmationScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviwIsiData() {
    CompleteDataScreen()
}

@Preview(showBackground = true)
@Composable
fun LupaSandiPreview() {
    ForgotPasswordScreen()
}
