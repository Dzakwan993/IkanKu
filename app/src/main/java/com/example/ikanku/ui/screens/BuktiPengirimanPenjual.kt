package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
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
fun OrderConfirmationDeliveryScreen() {
    var showRejectDialog by remember { mutableStateOf(false) }
    var showUploadDialog by remember { mutableStateOf(false) }
    var uploadedImage by remember { mutableStateOf(false) } // Track if an image is uploaded

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

                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* Handle accept order */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Atur Pengiriman", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            color = Color(0xFFE8E8E8), // Background color
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Address and order information
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

                // Order information
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ikan_nila),
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

                // Note for Seller
                Text("Catatan untuk penjual", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    "Mas saya lagi ada kegiatan diluar, tolong sampaikan ke kurir pesanan saya titip ke tetangga sebelah rumah saja, terimakasih.",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Payment and shipping method
                Text("Metode pembayaran : Non-COD", fontWeight = FontWeight.SemiBold)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("Pilih metode pengiriman : Dikirim", fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.height(8.dp))

                // Proof of Delivery Section
                Text("Bukti Pengiriman", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                if (uploadedImage) {
                    // Display uploaded image (sample image)
                    Image(
                        painter = painterResource(id = R.drawable.ikan_patin),
                        contentDescription = "Bukti Pengiriman",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f)
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    )
                } else {
                    // Show upload button if no image is uploaded
                    Button(
                        onClick = { showUploadDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f)
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.kamera), // Ganti dengan nama file drawable
                            contentDescription = "Upload Photo",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Upload Foto", color = Color.White)
                    }
                }
            }
        }

        if (showRejectDialog) {
            RejectOrderPayBottomSheet(
                onDismiss = { showRejectDialog = false },
                onSave = { /* Handle save reason */ showRejectDialog = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmationDeliveryScreenPreview() {
    OrderConfirmationDeliveryScreen()
}
