package com.example.ikanku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.model.Pembeli
import com.example.ikanku.model.Toko
import com.example.ikanku.ui.theme.IkanKuTheme
import com.example.ikanku.utils.SharedPreferencesHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val poppins = FontFamily(
            Font(R.font.poppins_regular, FontWeight.Normal),
            Font(R.font.poppins_bold, FontWeight.Bold)
        )
        enableEdgeToEdge()
        setContent {
            IkanKu()
        }
    }
}

@Composable
fun IkanKu() {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Periksa status login pengguna
    LaunchedEffect(Unit) {
        val isLoggedIn = SharedPreferencesHelper.isUserLoggedIn(context)
        val user = SharedPreferencesHelper.getUserData(context)

        if (isLoggedIn) {
            if (user is Toko) {
                navController.navigate("toko_saya_screen") {
                    popUpTo("login_screen") { inclusive = true }
                }
            } else if (user is Pembeli) {
                navController.navigate("beranda_screen") {
                    popUpTo("login_screen") { inclusive = true }
                }
            }
        } else {
            navController.navigate("login_screen") {
                popUpTo("login_screen") { inclusive = true }
            }
        }
    }

    NavGraph(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun IkanKuPreview() {
    IkanKuTheme {
        IkanKu()
    }
}
