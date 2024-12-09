package com.example.ikanku.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.* // Impor semua komponen yang diperlukan
import com.example.ikanku.model.PesananSelesai
import com.example.ikanku.R
import com.example.ikanku.viewmodel.LihatUlasanViewModel
import com.example.ikanku.viewmodel.OrderRejectedViewModel

@Composable
fun SelesaiScreen(
    navController: NavController
) {
    val selesaiList = listOf(
        PesananSelesai(
            name = "Ikan Nila",
            weightVariation = "1kg",
            price = "100.000",
            quantity = 2,
            imageResId = R.drawable.ikan_nila, // Sesuaikan dengan resource gambar
            status = "Selesai"
        ),
        PesananSelesai(
            name = "Ikan Tongkol",
            weightVariation = "500g",
            price = "50.000",
            quantity = 1,
            imageResId = R.drawable.ikan_patin, // Sesuaikan dengan resource gambar
            status = "Selesai"
        ),
        PesananSelesai(
            name = "Ika Gabus",
            weightVariation = "2kg",
            price = "200.000",
            quantity = 3,
            imageResId = R.drawable.ikan_angel, // Sesuaikan dengan resource gambar
            status = "Selesai"
        )
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "Pesanan Selesai",
                    onBackClick = { navController.navigate("profile_screen") }
                )
                OrderStatusTabs(
                    selectedTab = 3,
                    onTabSelected = { /* Aksi untuk memilih tab */ },
                    navController = navController
                )
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            if (selesaiList.isEmpty()) {
                item {
                    Text("Tidak ada pesanan selesai.")
                }
            } else {
                itemsIndexed(selesaiList) { index, selesai ->
                    LihatUlasanCard(
                        selesai = selesai, // PesananSelesai object
                        onClick = { navController.navigate("ulasan_saya") },
                        navController = navController
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSelesaiScreen() {
    val navController = rememberNavController()
    SelesaiScreen(navController = navController)
}
