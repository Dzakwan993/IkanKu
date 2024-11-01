package com.example.ikanku.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomBarButton
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.viewmodel.PembayaranViewModel

@Composable
fun ValidasiPembayaran() {
    val context = LocalContext.current

    Scaffold(
        containerColor = Color(0xFFF5F5F5), // Ubah warna background di sini
        topBar = {
            CustomTopAppBar(
                title = "Pembayaran",
                onBackClick = { /* Aksi kembali dapat ditambahkan di sini */ }
            )
        },
        bottomBar = {
            BottomBarButton {  }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Kartu Rekening Pembayaran
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E8E8)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rekening Pembayaran",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "123456789 34566 8890",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Ikon salin (copy)
                    Icon(
                        painter = painterResource(id = R.drawable.icon_copy), // replace with your copy icon resource
                        contentDescription = "Copy Icon",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                Toast.makeText(context, "Rekening disalin", Toast.LENGTH_SHORT).show()
                            }
                    )
                }
            }

            // Kartu Upload Bukti Pembayaran
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E8E8)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            Toast.makeText(context, "Klik untuk upload bukti pembayaran", Toast.LENGTH_SHORT).show()
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_bri), // replace with your upload icon resource
                        contentDescription = "Upload Icon",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Bukti Pembayaran",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewValidasiPembayaran() {
    ValidasiPembayaran()
}
