package com.example.ikanku.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.TopBarWithCart
import kotlinx.coroutines.launch
import androidx.compose.material3.SheetState
import androidx.compose.material.rememberModalBottomSheetState // This is the correct import
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.ikanku.R


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BiayaOngkosKirimScreen(
    onBackClick: () -> Unit,
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    // State untuk kecamatan terpilih
    var selectedKecamatan by remember { mutableStateOf<String?>(null) }
    var selectedHarga by remember { mutableStateOf<String>("") }

    // Data kecamatan dengan harga editable
    val ongkirList = remember {
        mutableStateListOf(
            "Batam Kota" to "Rp 25.000",
            "Belakang Padang" to "Rp 35.000",
            "Batu Aji" to "Rp 28.000",
            "Bengkong" to "Rp 24.000",
            "Bulang" to "Rp 40.000",
            "Galang" to "Rp 50.000",
            "Kabil" to "Rp 30.000",
            "Lubuk Baja" to "Rp 27.000",
            "Nongsa" to "Rp 29.000",
            "Sagulung" to "Rp 26.000",
            "Sekupang" to "Rp 30.000",
            "Sei Beduk" to "Rp 32.000"
        )
    }

    // State untuk membuka dialog edit harga
    var isEditDialogOpen by remember { mutableStateOf(false) }
    var currentKecamatan by remember { mutableStateOf("") }
    var currentHarga by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Biaya Ongkir", onBackClick = { navController.popBackStack() })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Daftar Biaya Ongkos Kirim",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Menggunakan LazyColumn untuk membuat daftar ongkir
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(ongkirList) { (kecamatan, harga) ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    currentKecamatan = kecamatan
                                    currentHarga = harga
                                    isEditDialogOpen = true
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White // Warna putih untuk Card
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp // Memberikan efek bayangan
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(kecamatan, fontSize = 16.sp)

                                // Adding a Spacer to push harga and Icon to the right
                                Spacer(modifier = Modifier.weight(1f))

                                // Align harga and Icon to the right side
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(harga, fontSize = 16.sp)
                                    Icon(
                                        painter = painterResource(id = R.drawable.pensil),
                                        contentDescription = "pensil",
                                        modifier = Modifier.padding(start = 8.dp) // Adjust space between harga and Icon
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    )

    // Dialog untuk mengedit harga ongkir
    if (isEditDialogOpen) {
        AlertDialog(
            onDismissRequest = { isEditDialogOpen = false },
            title = { Text("Edit Harga Ongkir") },
            text = {
                Column {
                    Text("Kecamatan: $currentKecamatan")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = currentHarga,
                        onValueChange = { currentHarga = it },
                        label = { Text("Harga Ongkir") }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val index = ongkirList.indexOfFirst { it.first == currentKecamatan }
                    if (index >= 0) {
                        ongkirList[index] = currentKecamatan to currentHarga
                    }
                    isEditDialogOpen = false
                }) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { isEditDialogOpen = false }) {
                    Text("Batal")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBiayaOngkosKirimScreen() {
    BiayaOngkosKirimScreen(
        onBackClick = {},
        navController = rememberNavController()
    )
}



