package com.example.ikanku.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.theme.IkanKuTheme
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahProdukScreen(
    onBackClick: () -> Unit = {},
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
                    modifier = Modifier
                        .fillMaxSize(),
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

            // Input Fields
            val fields = listOf(
                "Nama Produk*" to "Masukkan Nama Produk",
                "Deskripsi Produk*" to "Masukkan Deskripsi Produk",
                "Harga Produk*" to "Masukkan Harga Produk",
                "Stok Produk*" to "Masukkan Stok Produk",
                "Variasi*" to "Masukkan Variasi"
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

            // Dropdown Kategori
            KategoriDropdown()

            Spacer(modifier = Modifier.height(16.dp))

            // Jadwal Ditampilkan dengan Kalender
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

            // Menggunakan LaunchedEffect untuk memunculkan kalender
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

            // Diskon Field
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Atur Diskon") },
                placeholder = { Text("Masukkan Diskon", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Buttons
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
fun KategoriDropdown() {
    val kategoriList = listOf("Ikan Hias", "Ikan Air Tawar", "Bibit")
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TambahProdukPreview() {
    IkanKuTheme {
        TambahProdukScreen()
    }
}
