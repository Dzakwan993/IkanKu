package com.example.ikanku.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.TopBarLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationScreen(navController: NavController) {
    var otpCode by remember { mutableStateOf(List(6) { "" }) } // List of 6 empty strings
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    val auth = FirebaseAuth.getInstance()

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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
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
                        text = "Konfirmasi",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2151)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Silakan masukkan kode 6 digit yang dikirimkan melalui SMS ke 6285274086648",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    otpCode.forEachIndexed { index, char ->
                        BasicTextField(
                            value = char,
                            onValueChange = { value ->
                                if (value.length <= 1 && value.all { it.isDigit() }) {
                                    otpCode = otpCode.toMutableList().apply { this[index] = value }
                                    if (value.isNotEmpty() && index < otpCode.size - 1) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else if (index == otpCode.size - 1) {
                                        focusManager.clearFocus() // Clear focus after last input
                                    }
                                }
                            },
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                                .padding(4.dp)
                                .focusRequester(focusRequesters[index])
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dengan mengklik tombol \"Lanjut\", Anda menyetujui Kebijakan Privasi dan Ketentuan Penggunaan.",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val verificationId = navController.previousBackStackEntry?.savedStateHandle?.get<String>("verificationId")
                        if (verificationId != null) {
                            verifyOtp(
                                otpCode.joinToString(""),
                                verificationId,
                                auth,
                                navController
                            )
                        } else {
                            Toast.makeText(navController.context, "Verification ID tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Verifikasi", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

fun verifyOtp(otpCode: String, verificationId: String, auth: FirebaseAuth, navController: NavController) {
    if (otpCode.length == 6) {
        val credential = PhoneAuthProvider.getCredential(verificationId, otpCode)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User successfully authenticated
                    navController.navigate("daftar_data")
                } else {
                    // Show error message
                    Toast.makeText(navController.context, "Kode OTP salah", Toast.LENGTH_SHORT).show()
                }
            }
    } else {
        Toast.makeText(navController.context, "Kode OTP harus 6 digit", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationScreenPreview() {
    val navController = rememberNavController()
    ConfirmationScreen(navController = navController)
}
