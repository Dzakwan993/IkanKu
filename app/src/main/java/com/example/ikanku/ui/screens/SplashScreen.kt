package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.utils.SharedPreferencesHelper
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1500) // Tunggu 1.5 detik

        // Periksa status login
        val isUserLoggedIn = SharedPreferencesHelper.isUserLoggedIn(navController.context)
        val userType = SharedPreferencesHelper.getUserType(navController.context)

        // Navigasi sesuai status login
        if (isUserLoggedIn) {
            when (userType) {
                "pembeli" -> {
                    navController.navigate("beranda_screen") {
                        popUpTo(0) { inclusive = true } // Hapus semua stack sebelumnya
                    }
                }
                "toko" -> {
                    navController.navigate("toko_saya_screen") {
                        popUpTo(0) { inclusive = true } // Hapus semua stack sebelumnya
                    }
                }
                else -> {
                    navController.navigate("login_screen") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        } else {
            navController.navigate("login_screen") {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    // UI SplashScreen
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
                painter = painterResource(id = R.drawable.logo_ikanku),
                contentDescription = "Logo Ikan",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}

