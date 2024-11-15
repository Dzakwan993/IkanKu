package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.model.OrderStatusItem
import com.example.ikanku.model.ProfilPenjual
import com.example.ikanku.ui.components.*

@Composable
fun TokoSayaScreen(
    onBackClick: () -> Unit,
    onCartClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Custom AppBar dengan ikon kembali dan keranjang
        TopBarWithCart(
            title = "Toko Saya",
            onBackClick = onBackClick,
            onCartClick = onCartClick
        )

        // Profil Penjual Card
        ProfilPenjualCard(
            profilPenjual = ProfilPenjual(
                name = "Tibelat Farm",
                imageRes = R.drawable.beranda // Ganti dengan resource gambar profil yang sesuai
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Row untuk Status Penjualan dan lainnya
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Teks Status Penjualan
            Text(
                text = "Status Penjualan",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Tambahkan detail lainnya di sebelah kanan
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Lainnya >",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }

        // Status Pesanan Penjual
        OrderStatusPenjual(
            orderStatusItems = listOf(
                OrderStatusItem("Pesanan", R.drawable.pesanan),
                OrderStatusItem("Dikirim", R.drawable.pesanan_dikirim),
                OrderStatusItem("Pembatalan", R.drawable.pembatalan),
                OrderStatusItem("Selesai", R.drawable.pesanan_selesai)
            ),
            counts = listOf(3, 0, 5, 1), // Contoh jumlah pesanan untuk setiap status
            cardWidth = 100.dp,
            cardHeight = 120.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Individual Cards for Ganti Password, Email, and Pusat Bantuan
        GantiPasswordCard()
        EmailCard()
        PusatBantuanCard()

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Bar Penjual
        BottomNavBarPenjual()
    }
}

@Composable
fun GantiPasswordCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(8.dp), // Shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Ganti Password",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun EmailCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(8.dp), // Shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Email",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun PusatBantuanCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(8.dp), // Shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Pusat Bantuan",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TokoSayaScreenPreview() {
    TokoSayaScreen(
        onBackClick = { /* Handle back action */ },
        onCartClick = { /* Handle cart action */ }
    )
}