package com.example.ikanku

import android.graphics.Color

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.theme.IkanKuTheme

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
