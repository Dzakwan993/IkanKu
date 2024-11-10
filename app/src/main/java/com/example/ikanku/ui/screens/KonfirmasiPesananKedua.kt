package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationPayScreen() {
    var showRejectDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pesanan",
                onBackClick = { /* Handle back navigation */ }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { showRejectDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Tolak", color = Color.White, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* Handle accept order */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Terima", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            color = Color(0xFFE8E8E8), // Warna latar belakang
            shape = RoundedCornerShape(16.dp) // Sudut rounded 16 dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Alamat
                Text(text = "Alamat", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.alamat),
                        contentDescription = "Location Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Miftahul Fazra (6285274086648)")
                        Text(
                            "Perumahan tiban damai, Blok A No.35, RT.04, RW.07 Kelurahan Tiban indah, Sekupang",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Informasi Pesanan
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ikan_nila), // Ganti dengan gambar produk
                        contentDescription = "Product Image",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Ikan Nila", fontWeight = FontWeight.SemiBold)
                        Text("Pilih Variasi Berat 1 Kg", color = Color.Gray, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Ikan Nila X1", fontSize = 12.sp)
                            Text("Rp40.000", fontSize = 12.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Ongkir", fontSize = 12.sp)
                            Text("Rp5.000", fontSize = 12.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total", fontWeight = FontWeight.Bold)
                            Text("Rp45.000", fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Catatan untuk Penjual
                Text("Catatan untuk penjual", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    "Mas saya lagi ada kegiatan diluar, tolong sampaikan ke kurir pesanan saya titip ke tetangga sebelah rumah saja, terimakasih.",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Metode Pembayaran
                Text("Metode pembayaran : Non-COD", fontWeight = FontWeight.SemiBold)
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Metode Pengiriman
                Text("Pilih metode pengiriman : Dikirim", fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.height(8.dp))

                // Bukti Pembayaran
                Text("Bukti Pembayaran", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f) // Adjust aspect ratio as needed
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.LightGray
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bukti_pembayaran), // Ganti dengan gambar bukti pembayaran
                        contentDescription = "Bukti Pembayaran",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
            }
        }

        if (showRejectDialog) {
            RejectOrderBottomSheet(
                onDismiss = { showRejectDialog = false },
                onSave = { /* Handle save reason */ showRejectDialog = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RejectOrderPayBottomSheet(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit // Mengirimkan alasan penolakan sebagai parameter
) {
    var rejectionReason by remember { mutableStateOf("") } // State untuk menyimpan alasan penolakan

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Close icon
                IconButton(onClick = onDismiss, modifier = Modifier.align(Alignment.End)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }

                // Title
                Text("Alasan ditolak", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

                // TextField for reason
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = rejectionReason,
                    onValueChange = { rejectionReason = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFD3D3D3)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Save button
                Button(
                    onClick = { onSave(rejectionReason) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Simpan", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmationPayScreenPreview() {
    OrderConfirmationPayScreen()
}
