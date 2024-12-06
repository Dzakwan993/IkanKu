package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.TopBarLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }

    // Mendapatkan instance LoginViewModel
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory())

    // Mengambil status login dari ViewModel
    val loginStatus = loginViewModel.loginStatus.value

    Scaffold(
        topBar = {
            TopBarLogin(
                selectedTab = "login",
                onTabSelected = { /* Handle tab selection */ },
                navController = navController
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Input fields
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = { Text("No Ponsel Cth +6281234567891", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Kata sandi", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot password
            Text(
                text = "Lupa kata sandi",
                color = Color(0xFF177BCD),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 32.dp)
                    .clickable {
                        navController.navigate("lupa_sandi_pembeli")
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    loginViewModel.login(phoneNumber, password)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Login", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Atau",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Google Login Button
            OutlinedButton(
                onClick = { /* Handle Google login */ },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo), // Ikon Google dari drawable
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Login dengan Google", color = Color.Gray)
            }

            // Tampilkan pesan status login
            when (loginStatus) {
                is LoginViewModel.LoginStatus.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is LoginViewModel.LoginStatus.Success -> {
                    // Berhasil login, pindah ke halaman berikutnya
                    navController.navigate("beranda_screen")
                    LaunchedEffect(key1 = Unit) {
                        snackbarHostState.showSnackbar("Login Berhasil!")
                    }
                }
                is LoginViewModel.LoginStatus.Error -> {
                    LaunchedEffect(key1 = loginStatus.message) {
                        snackbarHostState.showSnackbar("Login Gagal: ${loginStatus.message}")
                    }
                }
                else -> {}
            }
        }
    }
}
