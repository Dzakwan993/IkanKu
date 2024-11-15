package com.example.ikanku.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomBarButton
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.theme.IkanKuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProdukScreen(
    modifier: Modifier = Modifier,
    onSimpanClick: () -> Unit = {},
    onHapusClick: () -> Unit = {},
    onLihatUlasanClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var showJadwalDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detail Produk",
                onBackClick = { /* Aksi kembali dapat ditambahkan di sini */ }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Foto Produk
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clickable { /* Tambahkan logika untuk memilih foto */ },
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E8E8))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_camera), // Ganti dengan ikon kamera Anda
                            contentDescription = "Upload Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tambah foto",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            val fields = listOf(
                "Nama Produk" to "Ikan Patin",
                "Deskripsi Produk" to "Deskripsi panjang ikan",
                "Harga Produk" to "Rp 30.500",
                "Stok Produk" to "50 kg",
                "Kategori" to "Ikan Air Tawar"
            )

            for (field in fields) {
                val label = field.first
                val hint = field.second
                var value by remember { mutableStateOf(hint) }
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text(label) },
                    placeholder = { Text(hint, color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFF2196F3)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Variasi Section
            var selectedVariants by remember { mutableStateOf(setOf<String>()) }
            val variants = listOf("250g", "500g", "1kg")

            Column {
                Text(
                    "Variasi:",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    variants.forEach { variant ->
                        FilterChip(
                            selected = selectedVariants.contains(variant),
                            onClick = {
                                selectedVariants = if (selectedVariants.contains(variant)) {
                                    selectedVariants - variant
                                } else {
                                    selectedVariants + variant
                                }
                            },
                            label = { Text(variant) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF2196F3),
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button Lihat Ulasan
            Button(
                onClick = onLihatUlasanClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8E8E8)),
                shape = RoundedCornerShape(50)
            ) {
                Text("Tambah Variasi", fontSize = 16.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Date Section
            Text(
                "Jadwal Ditampilkan:",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable { showJadwalDialog = true } // Klik pada Box
                        .padding(8.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "15/11/2024", // Teks jadwal
                        modifier = Modifier.padding(start = 16.dp),
                        color = Color.Gray
                    )
                }

                // Dialog Ditampilkan

                if (showJadwalDialog) {
                    AlertDialog(
                        onDismissRequest = { showJadwalDialog = false },
                        title = {
                            Text(
                                text = "Jadwal Ditampilkan",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        },
                        text = {
                            Text(
                                text = "Produk akan ditampilkan pada tanggal yang telah ditentukan. Simpan jadwal untuk mengonfirmasi perubahan ini.",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showJadwalDialog = false
                                    // Tambahkan aksi simpan jadwal jika diperlukan
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238)),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text("Simpan", color = Color.White)
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showJadwalDialog = false },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text("Batal", color = Color.Black)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onLihatUlasanClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Lihat Ulasan", fontSize = 16.sp, color = Color.White)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onHapusClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Hapus", fontSize = 16.sp)
                    }

                    Button(
                        onClick = onSimpanClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Simpan", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailProdukPreview2() {
    IkanKuTheme {
        DetailProdukScreen()
    }
}
