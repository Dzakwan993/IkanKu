package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
fun AlasanDitolak(navController: NavController) {
    var showRejectDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pesanan",
                onBackClick = {navController.popBackStack() }
            )
        },

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            item {
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
                        painter = painterResource(id = R.drawable.ikan_nila),
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
                            Text("Ikan Nila X1", fontSize = 12.sp)
                            Text("Rp40.000", fontSize = 12.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Ongkir", fontSize = 12.sp)
                            Text("Rp5.000", fontSize = 12.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total", fontWeight = FontWeight.Bold)
                            Text("Rp45.000", fontWeight = FontWeight.Bold)
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
                Text("Metode pembayaran : Non-COD", fontWeight = FontWeight.SemiBold)
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Metode Pengiriman
                Text("Pilih metode pengiriman : Dikirim", fontWeight = FontWeight.SemiBold)
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Spacer(modifier = Modifier.height(8.dp))

                // Bukti Pembayaran
                Text("Foto Pendukung", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f) // Adjust aspect ratio as needed
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.LightGray
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bukti_pembayaran),
                        contentDescription = "Bukti Pembayaran",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Alasan Ditolak
                Text(
                    text = "Alasan Ditolak",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                )
                Text(
                    text = "Pesanan ini ditolak karena tidak memenuhi kriteria yang diinginkan oleh penjual atau ada masalah lain yang mempengaruhi kelancaran transaksi.",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlasanDitolakPreview() {
    val navController = rememberNavController()
    AlasanDitolak(navController = navController)
}
