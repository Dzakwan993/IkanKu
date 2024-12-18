package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.TopBarHanyaJudul

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoInternetScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarHanyaJudul(
                title = "Eror 404", // Judul bisa disesuaikan jika diperlukan

            )
        },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Gambar untuk koneksi internet terputus
            Image(
                painter = painterResource(id = R.drawable.connection_lost), // Ganti dengan resource gambar yang sesuai
                contentDescription = "No Internet Connection",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Teks informasi
            Text(
                text = "Koneksi Internet Terputus!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoInternetScreenPreview() {
    val navController = rememberNavController()
    NoInternetScreen(navController = navController)
}
