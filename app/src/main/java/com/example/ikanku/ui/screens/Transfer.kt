package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

import TombolMasukkanKeranjang
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.draw.clip

import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

import com.example.ikanku.ui.components.CustomTopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(navController: NavController) {
    var photoUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { photoUris = photoUris + it }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Transfer") {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Card Rincian Pesanan
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Rincian Pesanan",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Subtotal", fontSize = 14.sp, color = Color.Gray)
                            Text(text = "Rp 45.000", fontSize = 14.sp, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Ongkos Kirim", fontSize = 14.sp, color = Color.Gray)
                            Text(text = "Rp 5.000", fontSize = 14.sp, color = Color.Black)
                        }
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Gray,
                            thickness = 1.dp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Total", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(
                                text = "Rp 50.000",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Rekening Pembayaran
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Rekening Pembayaran",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFE8E8E8), shape = RoundedCornerShape(16.dp))
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_bri),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = "9834762649", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card Upload Bukti Pembayaran
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Upload Bukti Pembayaran",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Menampilkan foto yang sudah dipilih
                            items(photoUris) { uri ->
                                Image(
                                    painter = rememberAsyncImagePainter(uri),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            // Kotak Tambah Foto
                            item {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(Color(0xFFE8E8E8), shape = RoundedCornerShape(16.dp))
                                        .clickable {
                                            launcher.launch("image/*")
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.icon_camera),
                                            contentDescription = null,
                                            tint = Color.Gray,
                                            modifier = Modifier.size(32.dp)
                                        )
                                        Text(
                                            text = "Tambah foto",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Tombol Simpan
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {
                TombolMasukkanKeranjang(
                    text = "Simpan",
                    onClick = {
                        navController.navigate("pesanan_sukses")
                    }
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewTransferScreen() {
    val navController = rememberNavController()
    TransferScreen(navController)
}


