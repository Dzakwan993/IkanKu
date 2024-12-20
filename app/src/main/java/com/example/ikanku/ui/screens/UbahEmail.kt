package com.example.ikanku.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeEmailScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Ubah Email",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                ,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                elevation = CardDefaults.cardElevation(8.dp), // Memberikan elevation
                shape = RoundedCornerShape(16.dp), // Opsional, untuk sudut membulat
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Email Baru",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)

                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Masukkan email baru", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF177BCD), // Warna kotak ketika checkbox dicentang
                            uncheckedColor = Color.Gray, // Warna kotak ketika checkbox tidak dicentang
                            checkmarkColor = Color.White // Warna tanda centang
                        )
                    )



                    Text(
                        text = "Kirimkan ke saya notifikasi mengenai update produk dan produk-produk dengan tawaran terbaik.",
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                }
            }


        }
            TombolMerahBiru(
                judulBiru = "Ubah",
                judulMerah = "Batal",
                onMerahClick = {
                    navController.popBackStack()
                },
                onBiruClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeEmailScreenPreview() {
    val navController = rememberNavController()
    ChangeEmailScreen(navController = navController)
}
