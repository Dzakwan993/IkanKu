package com.example.ikanku.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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


@Composable
fun TambahKategoriScreen(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onSimpanClick: (String) -> Unit = {}
) {
    var kategoriName by remember { mutableStateOf("") }
    var kategoriImage by remember { mutableStateOf("") } // Placeholder untuk path gambar

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Tambah Kategori", onBackClick ={navController.popBackStack()})
        },
        bottomBar = {
            Button(
                onClick = { onSimpanClick(kategoriName) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
            ) {
                Text("Tambah Variasi", color = Color.White, fontSize = 16.sp)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Gambar Variasi
            Text("Gambar Variasi*", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable {
                        // Tambahkan logika memilih gambar di sini
                    },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_camera), // Ganti dengan ID ikon Anda
                        contentDescription = "Tambah Foto",
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tambah foto", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nama Kategori
            OutlinedTextField(
                value = kategoriName,
                onValueChange = { kategoriName = it },
                label = { Text("Masukkan Nama Kategori") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color.LightGray
                )
            )


        }
    }
}


@Preview(showBackground = true)
@Composable
fun TambahKategoriScreenPreview() {
    MaterialTheme {
        TambahKategoriScreen(
            onBackClick = { /* Handle back click */ },
            onSimpanClick = { kategoriName ->
                // Handle simpan click
                println("Kategori yang disimpan: $kategoriName")
            },
            navController = rememberNavController()

        )
    }
}
