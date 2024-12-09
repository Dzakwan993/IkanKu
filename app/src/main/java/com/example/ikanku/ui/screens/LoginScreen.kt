package com.example.ikanku.ui.screens

import RetrofitInstance.apiService
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
                    if (phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                        loginViewModel.login(
                            phoneNumber = phoneNumber,
                            password = password,
                            onSuccess = { user ->
                                if (user is Toko) {
                                    SharedPreferencesHelper.saveUser(context, "toko", user)
                                    navController.navigate("toko_saya_screen") {
                                        popUpTo("login_screen") { inclusive = true } // Hapus login_screen dari stack
                                    }
                                } else if (user is Pembeli) {
                                    SharedPreferencesHelper.saveUser(context, "pembeli", user)
                                    navController.navigate("beranda_screen") {
                                        popUpTo("login_screen") { inclusive = true } // Hapus login_screen dari stack
                                    }
                                }
                            },
                            onError = { errorMessage ->
                                loginViewModel.setErrorMessage(errorMessage) // Atur error message di ViewModel
                            }
                        )
                    } else {
                        loginViewModel.setErrorMessage("Harap isi nomor ponsel dan kata sandi!")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF177BCD) // Ganti warna tombol di sini
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Login")
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
        }
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
            else -> {}
        }
    }
}

