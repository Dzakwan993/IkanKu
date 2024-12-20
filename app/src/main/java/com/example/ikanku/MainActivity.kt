package com.example.ikanku

import android.graphics.Color

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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.model.Pembeli
import com.example.ikanku.model.Toko
import com.example.ikanku.ui.theme.IkanKuTheme
import com.example.ikanku.utils.SharedPreferencesHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

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

//fun ComponentActivity.enableEdgeToEdge() {
//    // Pastikan konten mendukung edge-to-edge
//    WindowCompat.setDecorFitsSystemWindows(window, false)
//
//    // Atur warna status bar agar tetap biru
//    window.statusBarColor = Color.parseColor("#1976D2") // Ganti sesuai dengan warna aplikasi Anda
//
//    // Atur ikon status bar menjadi putih (agar terlihat di atas warna biru)
//    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
//}


@Composable
fun IkanKu() {
    val navController = rememberNavController()

    NavGraph(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun IkanKuPreview() {
    IkanKuTheme {
        IkanKu()
    }
}
