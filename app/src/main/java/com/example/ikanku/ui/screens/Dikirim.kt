package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Dikirim
import com.example.ikanku.ui.components.*

@Composable
fun DikirimScreen(
    onBackClick: () -> Unit,
    onDeliveryClick: () -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Dikirim",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Komponen tab status pesanan
            OrderStatusTabs(
                selectedTab = 2, // Tab 'Dikirim' dipilih
                onTabSelected = { index ->
                    // Handle tab selection jika diperlukan
                },
                navController = navController
            )

            // Daftar item dengan tombol "Detail Pengiriman"
            DikirimCard(
                dikirim = Dikirim(
                    name = "Ikan Nila",
                    weightVariation = "1 Kg",
                    price = "40.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_nila,
                    status = "" // Status tidak digunakan
                ),
                onDeliveryClick = onDeliveryClick,
                navController = navController
            )

            DikirimCard(
                dikirim = Dikirim(
                    name = "Ikan Gurame",
                    weightVariation = "2 Kg",
                    price = "80.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_nila,
                    status = "" // Status tidak digunakan
                ),
                onDeliveryClick = onDeliveryClick,
                navController = navController
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DikirimScreenPreview() {
    val navController = rememberNavController()
    DikirimScreen(
        onBackClick = { /* Preview action */ },
        onDeliveryClick = { /* Preview action */ },
        navController = navController
    )
}


