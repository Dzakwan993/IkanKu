package com.example.ikanku.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.theme.IkanKuTheme
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahProdukScreen(
    navController: NavController,
    onBackClick: () -> Unit = {navController.popBackStack()},
    onSimpanClick: () -> Unit = {},
    onBatalClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Tambah Produk Baru",
                onBackClick = onBackClick
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Product Image Section
            Text(
                "Foto Produk:",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(1) {
                    Card(
                        modifier = Modifier
                            .size(100.dp)
                            .clickable { /* Logic to add photo */ },
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
                                    painter = painterResource(id = R.drawable.icon_camera),
                                    contentDescription = "Tambah Foto",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Tambah Foto",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Input Fields Section
            val fields = listOf(
                "Nama Produk*" to "Masukkan Nama Produk",
                "Deskripsi Produk*" to "Masukkan Deskripsi Produk",
            )

            fields.forEach { (label, placeholder) ->
                var value by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text(label) },
                    placeholder = { Text(placeholder, color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1A73E8),
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            KategoriDropdown(navController = navController)


            Spacer(modifier = Modifier.height(16.dp))

            VariasiProdukCard {

            }
            // Category Dropdown Section


            Spacer(modifier = Modifier.height(16.dp))

            // Date Picker Section
            var isCalendarDialogVisible by remember { mutableStateOf(false) }
            var selectedDate by remember { mutableStateOf("Jadwal Ditampilkan") }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isCalendarDialogVisible = true }
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, MaterialTheme.shapes.medium),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = selectedDate,
                    modifier = Modifier.padding(16.dp),
                    color = if (selectedDate == "Jadwal Ditampilkan") Color.Gray else Color.Black
                )
            }

            // Date Picker Dialog
            if (isCalendarDialogVisible) {
                val context = LocalContext.current
                LaunchedEffect(Unit) {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            selectedDate = "$dayOfMonth/${month + 1}/$year"
                            isCalendarDialogVisible = false
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).apply {
                        setOnCancelListener { isCalendarDialogVisible = false }
                    }.show()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

DiskonProdukCard {

}

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Buttons Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onBatalClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238)),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Batal", color = Color.White, fontSize = 16.sp)
                }

                Button(
                    onClick = onSimpanClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Simpan", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun KategoriDropdown(navController: NavController) {
    val kategoriList = remember { mutableStateListOf("Ikan Hias", "Ikan Air Tawar", "Bibit") }
    var selectedKategori by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isDropdownExpanded = !isDropdownExpanded }
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .padding(12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = if (selectedKategori.isEmpty()) "Kategori" else selectedKategori,
                color = if (selectedKategori.isEmpty()) Color.Gray else Color.Black
            )
        }

        if (isDropdownExpanded) {
            kategoriList.forEach { kategori ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedKategori = kategori
                            isDropdownExpanded = false
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = kategori, fontSize = 16.sp, color = Color.Black)
                }
            }

            // Tombol Tambah Opsi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("tambah_kategori")}
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.tambah), // Ganti dengan ikon add Anda
                    contentDescription = "Tambah Opsi",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tambah Opsi", fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}



@Composable
fun VariasiProdukCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
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
                tint = Color.Gray
            )
        }
    }
}


@Composable
fun DiskonProdukCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
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
                text = "Atur Diskon",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Icon(
                painter = painterResource(id = R.drawable.lihat_detail),
                contentDescription = "Lihat Detail",
                tint = Color.Gray
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TambahProdukPreview() {
    IkanKuTheme {
        TambahProdukScreen(navController = rememberNavController())
    }
}
