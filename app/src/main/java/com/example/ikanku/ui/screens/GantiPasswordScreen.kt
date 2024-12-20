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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
                .padding(paddingValues)
                .fillMaxSize()
        )
        {
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
                .padding(vertical = 8.dp)
                ,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)

                ,

                horizontalAlignment = Alignment.Start,

            ) {
                Text(
                    text = "Ganti Password",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp).padding(top = 8.dp)

                )

                Spacer(modifier = Modifier.height(8.dp))

                PasswordField(
                    label = buildAnnotatedString {
                        append("Password Lama")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*") // Tanda bintang merah
                        }
                    },
                    placeholder = "Masukkan password lama",
                    value = oldPassword.value,
                    onValueChange = { oldPassword.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordField(
                    label = buildAnnotatedString {
                        append("Password Baru")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*") // Tanda bintang merah
                        }
                    },
                    placeholder = "Masukkan password baru",
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordField(
                    label = buildAnnotatedString {
                        append("Konfirmasi Password")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("*") // Tanda bintang merah
                        }
                    },
                    placeholder = "Konfirmasi password",
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it }
                )
            }


        }

    }
            Spacer(modifier = Modifier.weight(1f))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(label: AnnotatedString, placeholder: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Color.Gray) },
        placeholder = { Text(placeholder, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFE0E0E0),
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



