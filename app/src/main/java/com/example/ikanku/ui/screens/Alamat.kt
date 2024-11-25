package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Alamat",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Address Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Miftahul Fazra (6285274086648)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Perumahan tiban damai, Blok A No.35, RT.04, RW.07 Kelurahan\nTiban indah, Sekupang",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { /* Handle set as primary address */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Utama", color = Color.White)
                        }
                        IconButton(onClick = { /* Handle edit address */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.lihat_detail), // Replace with your arrow icon
                                contentDescription = "Edit Address",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Add New Address Button
            Button(
                onClick = { navController.navigate("tambah_alamat")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "+Tambah Alamat", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressScreenPreview() {
    val navController = rememberNavController()
    AddressScreen(navController = navController)
}
