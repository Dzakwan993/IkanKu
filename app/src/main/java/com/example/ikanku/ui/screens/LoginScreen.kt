package com.example.ikanku.ui.screens

import RetrofitInstance.apiService
import TombolMasukkanKeranjang
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Pembeli
import com.example.ikanku.model.Toko
import com.example.ikanku.ui.components.TopBarLogin
import com.example.ikanku.utils.SharedPreferencesHelper
import com.example.ikanku.viewmodel.LoginViewModel
import com.example.ikanku.viewmodel.LoginViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(apiService)
    )
    val loginStatus by loginViewModel.loginStatus.collectAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarLogin(
                selectedTab = "Login",
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
                .padding(paddingValues)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp), // Padding global untuk seluruh konten
            ) {
            // Input Nomor Telepon
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = { Text("No Ponsel (e.g. +6281234567891)", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input Kata Sandi
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Kata Sandi", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lupa Kata Sandi
            Text(
                text = "Lupa kata sandi?",
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable { navController.navigate("lupa_sandi_pembeli") }
                    .padding(vertical = 8.dp)
            )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Login
            TombolMasukkanKeranjang(
                onClick = {
                    navController.navigate("beranda_screen")
                },
                text = "Login",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Text Separator
            Text(
                text = "Atau",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Login Google
            OutlinedButton(
                onClick = { navController.navigate("beranda_screen") },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Login dengan Google")
            }

            // Menangani status login
            LaunchedEffect(loginStatus) {
                when (loginStatus) {
                    is LoginViewModel.LoginStatus.Error -> {
                        val errorMessage = (loginStatus as LoginViewModel.LoginStatus.Error).message
                        snackbarHostState.showSnackbar("Login Gagal: $errorMessage")
                    }
                    is LoginViewModel.LoginStatus.Loading -> {
                        snackbarHostState.showSnackbar("Sedang Memproses...")
                    }
                    else -> Unit
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}