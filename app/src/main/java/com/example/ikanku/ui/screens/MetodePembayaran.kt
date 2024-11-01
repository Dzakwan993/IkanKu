package com.example.ikanku.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
fun MetodePembayaran (){
    val context = LocalContext.current
    Scaffold(
        containerColor = Color(0xFFF5F5F5), // Ubah warna background di sini
        topBar = {
            CustomTopAppBar(
                title = "Metode Pembayaran",
                onBackClick = { /* Aksi kembali dapat ditambahkan di sini */ }
            )
        },
        bottomBar = {
            BottomBarButton { }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Section Rekening Pembayaran
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E8E8) // Warna Card di sini
                )

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon bank
                    Image(
                        painter = painterResource(id = R.drawable.icon_bri), // replace with your bank icon resource
                        contentDescription = "Bank Icon",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "123456789 34566 8890",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Section Upload Bukti Pembayaran
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFE8E8E8))
                    .clickable {
                        // Handle aksi upload bukti pembayaran
                        Toast.makeText(context, "Klik untuk upload bukti pembayaran", Toast.LENGTH_SHORT).show()
                    },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E8E8) // Warna Card di sini
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_camera), // replace with your upload icon resource
                        contentDescription = "Upload Icon",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Upload Bukti Pembayaran",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }

@Preview(showBackground = true)
@Composable
fun PreviewMetodePembayaran() {
    MetodePembayaran()
}
