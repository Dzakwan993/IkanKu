package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.* // Impor semua komponen yang diperlukan
import com.example.ikanku.model.PesananSelesai
import com.example.ikanku.R

@Composable
fun SelesaiScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Menampilkan CustomTopAppBar di bagian atas
        CustomTopAppBar(
            title = "Pesanan Selesai",
            onBackClick = { navController.popBackStack() }
        )

        // Menampilkan OrderStatusTabs di bawah CustomTopAppBar
        OrderStatusTabs(selectedTab = 3, onTabSelected = { /* Aksi untuk memilih tab */ }, navController = navController)

        // Contoh item selesai untuk ditampilkan di LihatUlasanCard dan BeriUlasanCard
        val sampleSelesai = PesananSelesai(
            name = "Ikan Nila",
            weightVariation = "1 Kg",
            price = "50.000",
            quantity = 1,
            imageResId = R.drawable.ikan_nila,
            status = "Pesanan Selesai"
        )

        // Menampilkan LihatUlasanCard
        LihatUlasanCard(
            selesai = sampleSelesai,
            onClick = { navController.navigate("ulasan_saya")},
            navController = navController
        )

        // Menampilkan BeriUlasanCard
        BeriUlasanCard(
            selesai = sampleSelesai,
            onClick = { /* Aksi untuk Beri Ulasan */ },
            navController = navController
        )

        Spacer(modifier = Modifier.weight(1f)) // Spasi fleksibel untuk menekan BottomNavBar ke bawah

        // Menampilkan BottomNavBar di bagian bawah
        BottomNavBar(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelesaiScreen() {
    val navController = rememberNavController()
    SelesaiScreen(navController = navController)
}
