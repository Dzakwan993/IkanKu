package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPesanan(navController: NavController) {
    var showRejectDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pesanan",
                onBackClick = { navController.popBackStack() }
            )
        },
    ) { innerPadding ->
        // Card wrapper dengan elevation dan bentuk rounded
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp), // Sudut rounded untuk card
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                // Alamat
                Text(text = "Alamat", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.alamat),
                        contentDescription = "Location Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Miftahul Fazra (6285274086648)")
                        Text(
                            "Perumahan tiban damai, Blok A No.35, RT.04, RW.07 Kelurahan Tiban indah, Sekupang",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Informasi Pesanan
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ikan_nila), // Ganti dengan gambar produk
                        contentDescription = "Product Image",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Ikan Nila", fontWeight = FontWeight.SemiBold)
                        Text("Pilih Variasi Berat 1 Kg", color = Color.Gray, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Rp50.000", fontWeight = FontWeight.Bold)
                            Text("x1", fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Catatan untuk Penjual
                Text("Catatan untuk penjual", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    "Mas saya lagi ada kegiatan diluar, tolong sampaikan ke kurir pesanan saya titip ke tetangga sebelah rumah saja, terimakasih.",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Metode Pembayaran
                Text("Metode pembayaran : Transfer", fontWeight = FontWeight.SemiBold)
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Metode Pengiriman
                Text("Pilih metode pengiriman : Antar", fontWeight = FontWeight.SemiBold)

                Text(
                    text = "Bukti Pembayaran",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0xFFE8E8E8),
                            shape = RoundedCornerShape(16.dp)
                        ) // Warna kuning dengan sudut melengkung
                        .padding(16.dp), // Padding di sekitar gambar
                    contentAlignment = Alignment.Center // Posisi gambar di tengah
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.detail_pengiriman), // Ganti dengan ID gambar bukti pembayaran
                        contentDescription = "Delivery Proof",
                        modifier = Modifier
                            .size(200.dp) // Ukuran gambar
                    )
                }
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun DetailPesananPreview() {
    val navController = rememberNavController()
    DetailPesanan(navController = navController)
}
