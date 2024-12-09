package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.TopBarLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarLogin(
                selectedTab = "Daftar",
                onTabSelected = { /* Handle tab selection */ },
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Menyebarkan konten di antara kolom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Bagian atas dengan ikon kembali dan judul
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Konfirmasi",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2151)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Silakan masukkan kode 6 digit yang dikirimkan\nmelalui SMS ke 6285274086648",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()

                )

                Spacer(modifier = Modifier.height(24.dp))

                // Kode input boxes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(6) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            // Placeholder untuk setiap input kode
                            Text(
                                text = "",
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Padding untuk bagian bawah
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Kebijakan Privasi
                Text(
                    text = buildAnnotatedString {
                        append("Dengan mengklik tombol ")
                        append("\"Lanjut\", Anda\nmenyetujui ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Kebijakan Privasi")
                        }
                        append(" dan ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Ketentuan Penggunaan.")
                        }
                    },
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)


                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Lanjut
                TombolMasukkanKeranjang(
                    onClick = { navController.navigate("daftar_data") },
                    text = "Lanjut",
                    modifier = Modifier
                        .fillMaxWidth()

                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationScreenPreview() {
    val navController = rememberNavController()
    ConfirmationScreen(navController = navController)
}
