package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ikanku.R
import com.example.ikanku.model.Dikirim
import com.example.ikanku.ui.components.*

@Composable
fun DikirimScreen(
    onBackClick: () -> Unit,
    onDeliveryClick: () -> Unit,
    onReorderClick: () -> Unit
) {
    // Menggunakan Scaffold untuk tata letak yang fleksibel
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Dikirim",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            BottomNavBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp) // Jarak pada konten utama
        ) {
            // Komponen tab untuk status pesanan
            OrderStatusTabs(
                selectedTab = 2, // Mengatur tab yang dipilih (misalnya 'Dikirim')
                onTabSelected = { index ->
                    // Handle tab selection
                }
            )

            // Komponen kartu pesanan dikirim
            DikirimCard(
                dikirim = Dikirim(
                    name = "Ikan Nila",
                    weightVariation = "1 Kg",
                    price = "40.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_nila,
                    status = "Pesanan Anda sedang dikirim"
                ),
                onDeliveryClick = onDeliveryClick
            )

            // Komponen kartu pesanan diterima
            PesananDiterimaCard(
                dikirim = Dikirim(
                    name = "Ikan Nila",
                    weightVariation = "1 Kg",
                    price = "40.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_nila,
                    status = "Pesanan Diterima"
                ),
                onReorderClick = onReorderClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DikirimScreenPreview() {
    DikirimScreen(
        onBackClick = { /* Preview action for back click */ },
        onDeliveryClick = { /* Preview action for delivery click */ },
        onReorderClick = { /* Preview action for reorder click */ }
    )
}
