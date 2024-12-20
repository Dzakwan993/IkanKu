package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar // Import CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpCenterScreen(navController: NavController) {
    var whatsappNumber by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pusat Bantuan",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Apakah anda butuh bantuan atau pertanyaan? Kirimkan pesan kepada kami melalui Whatsapp!",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Image in the center
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f), // Membuat gambar fleksibel
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pusatbantuanimage), // Ganti dengan resource gambar Anda
                    contentDescription = "Help Center Illustration",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            // Button Section
            TombolMasukkanKeranjang(
                text = "Lanjutkan ke WhatsApp",
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HelpCenterScreenPreview() {
    val navController = rememberNavController()
    HelpCenterScreen(navController = navController)
}




