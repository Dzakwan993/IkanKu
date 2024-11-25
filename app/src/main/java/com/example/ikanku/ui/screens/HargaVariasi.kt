package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CustomTopAppBar


@Composable
fun HargaVariasiScreen(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onSimpanClick: (List<Pair<String, Pair<String, String>>>) -> Unit = {}
) {
    // Data variasi sebagai List<Pair<String, Pair<String, String>>>
    val variasiList = remember {
        mutableStateListOf(
            "500gr" to Pair("", ""), // Variasi dengan harga dan stok kosong
            "1 kg" to Pair("", "")
        )
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Harga Variasi", onBackClick = {navController.popBackStack()})
        },
        bottomBar = {
            Button(
                onClick = { onSimpanClick(variasiList) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
            ) {
                Text("Simpan", color = Color.White, fontSize = 16.sp)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Atur harga dan Stok sesuai berat untuk semua variasi produk",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Iterasi melalui variasiList
            variasiList.forEachIndexed { index, item ->
                val (variasi, hargaStokPair) = item
                val (harga, stok) = hargaStokPair

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = variasi,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = harga,
                            onValueChange = {
                                // Update harga
                                variasiList[index] = variasi to (it to stok)
                            },
                            label = { Text("Masukkan Harga") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp), // Rounded corner 8dp
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1A73E8),
                                unfocusedBorderColor = Color.LightGray
                            )
                        )
                        OutlinedTextField(
                            value = stok,
                            onValueChange = {
                                // Update stok
                                variasiList[index] = variasi to (harga to it)
                            },
                            label = { Text("Masukkan Stok") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp), // Rounded corner 8dp
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1A73E8),
                                unfocusedBorderColor = Color.LightGray
                            )
                        )
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HargaVariasiScreenPreview() {
    MaterialTheme {
        HargaVariasiScreen(
            onBackClick = { /* Handle back click */ },
            onSimpanClick = { hasil ->
                println("Hasil Variasi: $hasil")
            },
            navController = rememberNavController()
        )
    }
}
