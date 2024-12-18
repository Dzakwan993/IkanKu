package com.example.ikanku.ui.screens

import Rekomendasi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.CartItemModel
import com.example.ikanku.viewmodel.BerandaViewModel
import com.example.ikanku.viewmodel.ShoppingCartViewModel
@Composable
fun StartupScreen(
    onWebsiteClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF177BCD))
            .padding(horizontal = 16.dp) // Padding for screen edges
    ) {
        // Logo Column in the center of the screen
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center) // Center logo in the screen
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ikanku),
                contentDescription = "Logo Ikanku",
                modifier = Modifier.size(200.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )



            Text(
                text = "Hidup lebih sehat dengan ikan segar di setiap santapan",
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        // Buttons Column at the bottom of the screen
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align buttons to the bottom
                .padding(bottom = 32.dp) // Padding from the bottom edge
        ) {
            Button(
                onClick = onWebsiteClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Website Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Jelajahi Website kami", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Masuk", color = Color.Black)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun StartupScreenPreview() {
    StartupScreen()
}




