package com.example.ikanku.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.ui.components.BottomBarButton
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.theme.IkanKuTheme

@Composable
fun VariasiScreen(
    modifier: Modifier = Modifier,
    onSimpanClick: (List<VariasiData>) -> Unit = {}
) {
    var variasiList by remember { mutableStateOf(mutableListOf<VariasiData>()) }

    // Menambahkan variasi contoh awal
    if (variasiList.isEmpty()) {
        variasiList.add(VariasiData("Berat", mutableListOf("500gr", "1 kg")))
        variasiList.add(VariasiData("Isi", mutableListOf("2 ekor", "3 ekor", "4 ekor")))
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Variasi",
                onBackClick = { /* Aksi kembali dapat ditambahkan di sini */ }
            )
        },
        bottomBar = {
            BottomBarButton {
                // Tombol simpan akan memanggil `onSimpanClick` dan memberikan data variasi terbaru
                onSimpanClick(variasiList)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            variasiList.forEachIndexed { index, variasi ->
                VariasiCard(
                    variasi = variasi,
                    onDeleteVariasi = { variasiList.removeAt(index) },
                    onAddOption = { variasi.options.add("") },
                    onEditOption = { optionIndex, newValue ->
                        variasi.options[optionIndex] = newValue
                    },
                    onDeleteOption = { optionIndex ->
                        variasi.options.removeAt(optionIndex)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Tambah Variasi
            Text(
                text = "+Tambah Variasi",
                fontSize = 16.sp,
                color = Color(0xFF1A73E8),
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        variasiList.add(VariasiData("Nama Variasi", mutableListOf()))
                    }
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun VariasiCard(
    variasi: VariasiData,
    onDeleteVariasi: () -> Unit,
    onAddOption: () -> Unit,
    onEditOption: (Int, String) -> Unit,
    onDeleteOption: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Warna putih untuk Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = variasi.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Hapus",
                    fontSize = 14.sp,
                    color = Color(0xFFFF4238),
                    modifier = Modifier.clickable { onDeleteVariasi() }
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Gray
            )

            // Daftar opsi
            variasi.options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = option,
                        onValueChange = { onEditOption(index, it) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1A73E8),
                            unfocusedBorderColor = Color.LightGray
                        )
                    )
                    IconButton(onClick = { onDeleteOption(index) }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Delete", tint = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tombol Tambah Opsi
            Text(
                text = "+Tambah Opsi",
                fontSize = 14.sp,
                color = Color(0xFF1A73E8),
                modifier = Modifier.clickable { onAddOption() }
            )
        }
    }
}

data class VariasiData(
    val name: String,
    val options: MutableList<String>
)

@Preview(showBackground = true)
@Composable
fun VariasiScreenPreview() {
    IkanKuTheme {
        VariasiScreen(onSimpanClick = { variasiList ->
            // Contoh output untuk data yang disimpan
            println("Data yang disimpan:")
            variasiList.forEach { variasi ->
                println("${variasi.name}: ${variasi.options.joinToString(", ")}")
            }
        })
    }
}
