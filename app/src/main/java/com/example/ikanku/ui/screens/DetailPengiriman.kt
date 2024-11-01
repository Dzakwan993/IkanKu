package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun DetailPengiriman() {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detail Pengiriman",
                onBackClick = { /* Handle back navigation */ }
            )
        },
        bottomBar = {
            BottomNavBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Main Card containing all sections
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Delivery Status with Icon
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_truk), // Replace with actual icon resource
                            contentDescription = "Delivery Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Dalam proses pengiriman",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)

                    // Address Section with Icon
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_map), // Replace with actual icon resource
                            contentDescription = "Location Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Alamat", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Miftahul Fazra (085274088649)")
                            Text("Perumahan tiban damai, Blok A No.35, RT.04, RW.07 Kelurahan Tiban indah, Sekupang")
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)

                    // Item Details Section
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ikan_patin),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Ikan Nila", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Pilih Variasi Berat 1 Kg")
                            Text("Rp 45.000 x1")
                            Text(
                                text = "Catatan untuk penjual:\nMas saya lagi ada kegiatan diluar, tolong sampaikan ke kurir pesan saya titip ke tetangga sebelah rumah saja, terimakasih.",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)

                    // Payment Method Section
                    Text("Metode pembayaran: Non-COD", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Pilih metode pengiriman: Dikirim")
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)

                    // Nested Card for Delivery Proof Section
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFC4C4C4)) // Updated color to C4C4C4
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Bukti Pengiriman", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(8.dp))

                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.bukti_map1),
                                    contentDescription = null,
                                    modifier = Modifier.size(120.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.bukti_map2),
                                    contentDescription = null,
                                    modifier = Modifier.size(120.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailPengiriman() {
    DetailPengiriman()
}
