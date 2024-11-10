package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomBarButton
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ulasan() {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }
    var showNotification by remember { mutableStateOf(false) }

    if (showNotification) {
        AlertDialog(
            onDismissRequest = { showNotification = false },
            confirmButton = {
                Button(onClick = { showNotification = false }) {
                    Text("OK", color = Color.White)
                }
            },
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.check), // Replace with check icon resource
                        contentDescription = "Success",
                        tint = Color.Green,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ulasan Terkirim, Terima Kasih!", fontWeight = FontWeight.Bold)
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }

    Scaffold(
        containerColor = Color(0xFFF5F5F5), // Ubah warna background di sini
        topBar = {
            CustomTopAppBar(
                title = "Ulasan",
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Align content to the center horizontally
        ) {
            // Main Card containing all sections
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E8E8))
            ) {
                // Product Info Section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ikan_nila), // replace with actual image resource
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.Gray, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Ikan Nila", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Pilih Variasi Berat 1 kg", fontSize = 14.sp)
                        Text("Rp 40.000 x1", fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Centered Rating Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("*Bagaimana pengalaman Anda?", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = if (i <= rating) R.drawable.star_fill else R.drawable.star_empty
                                ), // replace with your star icons
                                contentDescription = null,
                                tint = if (i <= rating) Color(0xFFFFC107) else Color.Gray,
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(4.dp)
                                    .clickable { rating = i }
                            )
                        }
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))

                // Comment Section
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), // Memberi jarak di sekitar Card
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE0E0E0) // Warna latar belakang Card
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp) // Padding dalam card untuk memberi ruang
                    ) {
                        // Judul Comment Section
                        Text(
                            text = "Tuliskan Penilaian Anda",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Input field untuk komentar
                        OutlinedTextField(
                            value = comment,
                            onValueChange = { comment = it },
                            placeholder = {
                                Text(
                                    "Ceritakan pengalamanmu tentang kualitas ikan dan pelayanan penjual",
                                    fontSize = 14.sp,
                                    fontStyle = FontStyle.Italic // Menjadikan teks miring
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }


                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUlasan() {
    Ulasan()
}
