package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.ui.components.* // Impor semua komponen yang diperlukan
import com.example.ikanku.model.PesananSelesai
import com.example.ikanku.R

@Composable
fun SelesaiScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Menampilkan CustomTopAppBar di bagian atas
        CustomTopAppBar(
            title = "Pesanan Selesai",
            onBackClick = { /* Tambahkan aksi kembali, jika diperlukan */ }
        )

        // Menampilkan OrderStatusTabs di bawah CustomTopAppBar
        OrderStatusTabs(selectedTab = 3, onTabSelected = { /* Aksi untuk memilih tab */ })

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
            onReorderClick = { /* Aksi untuk Lihat Ulasan */ }
        )

        // Menampilkan BeriUlasanCard
        BeriUlasanCard(
            selesai = sampleSelesai,
            onReorderClick = { /* Aksi untuk Beri Ulasan */ }
        )

        Spacer(modifier = Modifier.weight(1f)) // Spasi fleksibel untuk menekan BottomNavBar ke bawah

        // Menampilkan BottomNavBar di bagian bawah
        BottomNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelesaiScreen() {
    SelesaiScreen()
}
