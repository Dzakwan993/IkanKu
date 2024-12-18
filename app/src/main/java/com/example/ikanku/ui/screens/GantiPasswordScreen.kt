package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun ChangePasswordScreen(navController: NavController) {
    val oldPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Ganti Password",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                PasswordField(
                    label = "Password Lama*",
                    placeholder = "Masukkan password lama",
                    value = oldPassword.value,
                    onValueChange = { oldPassword.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordField(
                    label = "Password Baru*",
                    placeholder = "Masukkan password baru",
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordField(
                    label = "Konfirmasi Password*",
                    placeholder = "Konfirmasi password",
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Handle cancel action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Batal", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { /* Handle change action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Ubah", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(label: String, placeholder: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Color.Black) },
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFF5F5F5),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProfil() {
    val navController = rememberNavController()
    DataProfileScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    val navController = rememberNavController()
    ChangePasswordScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewUbahEmail() {
    val navController = rememberNavController()
    ChangeEmailScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewUbahEmailOTP() {
    VerifyEmailScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewPusatBantuan() {
    val navController = rememberNavController()
    HelpCenterScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewAlamat() {
    val navController = rememberNavController()
    AddressScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewTambahAlamat() {
    val navController = rememberNavController()
    AddAddressScreen(navController = navController)
}



