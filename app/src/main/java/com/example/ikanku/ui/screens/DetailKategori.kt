package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun DeliveryDetailScreen(onBackClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            title = "Detail Pengiriman",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Dalam proses pengiriman",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF177BCD),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Address section
                Text(
                    text = "Alamat",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "Miftahul Fa'za (6285274068648)\n" +
                            "Perumahan tiban damai, Blok A No.35,\n" +
                            "RT.04, RW.07 Kelurahan Tibab indah, Sekupang",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Item section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ikan_nila), // Replace with your item image resource
                        contentDescription = "Ikan Nila",
                        modifier = Modifier.size(60.dp), // Square size for item image
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Ikan Nila",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Pilih Variasi Berat 1 Kg",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Total: Rp 45.000",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                // Notes to buyer section
                Text(
                    text = "Catatan untuk Pembeli",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "Pesanan anda dalam pengiriman. Terima kasih telah berbelanja di toko kami. Semoga Anda puas dengan layanan kami.",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Payment and shipping method section
                Text(
                    text = "Metode pembayaran : NON COD",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = "Pilih metode pengiriman : Dikirim",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                // Delivery proof section with two rectangular images
                Text(
                    text = "Bukti Pengiriman",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.detail_pengiriman), // Replace with first proof image resource
                        contentDescription = "Delivery Proof 1",
                        modifier = Modifier
                            .weight(1f)
                            .height(500.dp), // Sets a fixed height, width adjusts based on aspect ratio
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.detail_alamat), // Replace with second proof image resource
                        contentDescription = "Delivery Proof 2",
                        modifier = Modifier
                            .weight(1f)
                            .height(500.dp), // Sets a fixed height, width adjusts based on aspect ratio
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeliveryDetailScreen() {
    DeliveryDetailScreen(onBackClick = { /* Handle back action */ })
}
