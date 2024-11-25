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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomBarButton
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.theme.IkanKuTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProdukScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSimpanClick: () -> Unit = {},
    onHapusClick: () -> Unit = {},
    onLihatUlasanClick: () -> Unit = { navController.navigate("ulasan_produk_penjual") },

) {
    val scrollState = rememberScrollState()
    var showJadwalDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Detail Produk",
                onBackClick = { navController.popBackStack()}
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

            // Bagian Foto Produk
            Text(
                "Foto Produk:",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val fotoAsli = listOf(
                    R.drawable.ikan_nila,
                    R.drawable.ikan_nila
                )

                items(fotoAsli.size + 1) { index ->
                    if (index < fotoAsli.size) {
                        FotoCard(fotoResId = fotoAsli[index], onClick = { /* Logika untuk gambar */ })
                    } else {
                        TambahFotoCard(onClick = { /* Logika tambah foto */ })
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bagian Form
            val fields = listOf(
                "Nama Produk" to "Ikan Patin",
                "Deskripsi Produk" to "Deskripsi panjang ikan"
            )

            fields.forEach { (label, hint) ->
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

            // Dropdown untuk Kategori
            var kategori by remember { mutableStateOf("") }
            val kategoriList = listOf("Ikan Air Tawar", "Ikan Laut", "Produk Olahan")
            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                OutlinedTextField(
                    value = kategori,
                    onValueChange = { kategori = it },
                    label = { Text("Kategori") },
                    placeholder = { Text("Pilih Kategori") },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFF2196F3)
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    kategoriList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                kategori = item
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bagian Variasi
            VariasiCard(onClick = { /* Logika variasi */ }, navController = navController)


            Spacer(modifier = Modifier.height(16.dp))

            JadwalCard(
                date = "15/11/2024",
                showDialog = showJadwalDialog,
                onShowDialogChange = { showJadwalDialog = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DiskonCard (onClick = { /* Logika variasi */ }, navController = navController)

            Spacer(modifier = Modifier.height(24.dp))

            // Bagian Button
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
                        Text("Simpan", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun FotoCard(fotoResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = fotoResId),
                contentDescription = "Foto Produk",
                modifier = Modifier.size(80.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun TambahFotoCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E8E8)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_camera),
                    contentDescription = "Tambah Foto",
                    tint = Color.Gray,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Tambah Foto", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun VariasiCard(onClick: () -> Unit, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { navController.navigate("atur_variasi")},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Atur warna kartu menjadi putih
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Variasi",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Icon(
                painter = painterResource(id = R.drawable.lihat_detail),
                contentDescription = "Lihat Detail",
                tint = Color.Gray,
                modifier = Modifier.clickable{navController.navigate("atur_variasi")}
            )
        }
    }
}

@Composable
fun DiskonCard(onClick: () -> Unit, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {navController.navigate("atur_diskon")},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Atur warna kartu menjadi putih
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = " Atur Diskon",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Icon(
                painter = painterResource(id = R.drawable.lihat_detail),
                contentDescription = "Lihat Detail",
                tint = Color.Gray,
                modifier = Modifier.clickable{navController.navigate("atur_diskon")}
            )
        }
    }
}

@Composable
fun JadwalCard(
    date: String,
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { onShowDialogChange(true) }
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = date,
                modifier = Modifier.padding(start = 16.dp),
                color = Color.Gray
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { onShowDialogChange(false) },
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
                        onClick = { onShowDialogChange(false) }
                    ) {
                        Text("Simpan")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun DetailProdukScreenPreview() {

    IkanKuTheme {
        DetailProdukScreen(navController = rememberNavController())
    }
}
