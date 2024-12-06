    package com.example.ikanku.ui.screens

    import android.widget.Toast
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.example.ikanku.ui.components.TopBarLogin
    import com.google.firebase.FirebaseException
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.auth.PhoneAuthCredential
    import com.google.firebase.auth.PhoneAuthProvider

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterScreen(navController: NavController) {
        var phoneNumber by remember { mutableStateOf("") }
        val formattedPhoneNumber = formatPhoneNumber(phoneNumber) // Format nomor ponsel saat diinput
        // Validasi nomor ponsel (dimulai dengan +62 atau 62 dan panjangnya lebih dari 9 karakter)
        val isPhoneNumberValid = (formattedPhoneNumber.startsWith("+62") || formattedPhoneNumber.startsWith("62")) && formattedPhoneNumber.length >= 12

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
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Masukkan Nomor Ponsel",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A2151),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Input nomor ponsel
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        placeholder = { Text("Nomor Ponsel Cth +6282387436427", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Keterangan
                    Text(
                        text = "* Anda akan menerima SMS berisi kode daftar.",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    // Kebijakan Privasi
                    Text(
                        text = "Dengan mengklik tombol \"Lanjut\", Anda menyetujui Kebijakan Privasi dan Ketentuan Penggunaan.",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tombol Lanjut
                    Button(
                        onClick = {
                            if (isPhoneNumberValid) {
                                sendVerificationCode(formattedPhoneNumber, auth, navController)
                            } else {
                                // Show error message if phone number is invalid
                                Toast.makeText(navController.context, "Nomor telepon tidak valid", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Lanjut", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }

    fun formatPhoneNumber(phoneNumber: String): String {
        // Menghapus angka 0 di depan dan menggantinya dengan +62
        return when {
            phoneNumber.startsWith("08") -> "+62" + phoneNumber.substring(1)
            phoneNumber.startsWith("62") -> "+" + phoneNumber // Menangani nomor yang dimulai dengan 62
            else -> phoneNumber // Jika nomor sudah dalam format yang benar, tidak perlu diubah
        }
    }



    fun sendVerificationCode(phoneNumber: String, auth: FirebaseAuth, navController: NavController) {
        val formattedPhoneNumber = formatPhoneNumber(phoneNumber)
        val phoneAuthProvider = PhoneAuthProvider.getInstance(auth)

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Autentikasi berhasil, lanjutkan ke halaman OTP
                navController.navigate("daftar_otp")
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Toast.makeText(navController.context, "Verifikasi Gagal: ${exception.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // Simpan verificationId untuk digunakan saat verifikasi OTP
                navController.currentBackStackEntry?.savedStateHandle?.set("verificationId", verificationId)
                navController.navigate("daftar_otp")
            }
        }

        phoneAuthProvider.verifyPhoneNumber(
            formattedPhoneNumber, // Nomor telepon yang diformat
            60, // Timeout dalam detik
            java.util.concurrent.TimeUnit.SECONDS,
            navController.context as android.app.Activity, // Aktivitas yang aktif
            callbacks // Pass the callback instance here
        )
    }



    @Preview(showBackground = true)
    @Composable
    fun RegisterScreenPreviewWithoutNav() {
        MaterialTheme {
            // Render RegisterScreen tanpa menggunakan navController
            RegisterScreen(navController = rememberNavController())
        }
    }







