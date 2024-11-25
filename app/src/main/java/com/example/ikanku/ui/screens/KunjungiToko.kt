package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreVisitScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Kunjungi Toko",
                onBackClick = { navController.popBackStack()}
            )
        },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Profile Card
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profil_kucing), // Replace with actual image resource
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.Gray, CircleShape)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Tibelat Farm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "4.5",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Contact Information
            InfoRow(iconRes = R.drawable.telepon, text = "62 81266680075")
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Opening Hours
            InfoRow(iconRes = R.drawable.centang_bulat, text = "Setiap hari dari jam (06.00-18.00)")
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Description
            InfoRow(
                iconRes = R.drawable.info,
                text = "Tempat Budidaya Ikan Air Tawar, Restoran, Kolam Pemancingan (timbang kilo), Tempat Edukasi Seputar Budidaya Ikan Air Tawar untuk anak TK/PAUD, SD, SMP, SMA dan Mahasiswa, serta Pelatihan."
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Address
            InfoRow(
                iconRes = R.drawable.alamat,
                text = "Jl. KH Ahmad Dahlan, sei Temiang, RT.02/RW.07, Tj. Riau, Kec. Sekupang, Kota Batam, Kepulauan Riau 29425"
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Delivery Information
            InfoRow(
                iconRes = R.drawable.mobil_box,
                text = "Pengiriman akan di proses setelah 2 jam pemesanan"
            )
        }
    }
}

@Composable
fun InfoRow(iconRes: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StoreVisitScreenPreview() {
    val navController = rememberNavController()
    StoreVisitScreen(navController = navController)
}
