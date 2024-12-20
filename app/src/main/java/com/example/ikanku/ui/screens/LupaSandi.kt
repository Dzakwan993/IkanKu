package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.TopBarLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarLogin(
                selectedTab = "Login",
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Lupa Kata sandi?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2151)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Input field for email
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = { Text("Masukkan alamat email anda", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_mail), // Replace with email icon in drawable
                            contentDescription = "Email Icon",
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Informative text
                Text(
                    text = "* Kami akan mengirimkan pesan kepada anda untuk mengatur\natau mereset kata sandi baru anda",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            // "Lanjut" button at the bottom
            TombolMasukkanKeranjang(
                onClick = { navController.navigate("beranda_screen") },
                text = "Kirim",
                modifier = Modifier
                    .fillMaxWidth()

            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    val navController = rememberNavController()
    ForgotPasswordScreen(navController = navController)
}
