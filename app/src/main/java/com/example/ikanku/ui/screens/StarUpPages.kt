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
import com.example.ikanku.R
import com.example.ikanku.model.CartItemModel
import com.example.ikanku.viewmodel.BerandaViewModel
import com.example.ikanku.viewmodel.ShoppingCartViewModel

@Composable
fun StartupScreen(
    onWebsiteClick:  () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF177BCD))
            .padding(horizontal = 32.dp), // General padding for screen edges
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Arrange items with space between them
    ) {
        Spacer(modifier = Modifier.height(48.dp)) // Optional top spacer

        // Logo and App Name
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ikanku),
                contentDescription = "Logo Ikanku",
                modifier = Modifier.size(200.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )


        }

        // Buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {


            Text(
                text = "Hidup lebih sehat dengan ikan segar di setiap santapan",
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onWebsiteClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
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

@Preview(showBackground = true)
@Composable
fun BerandaPreview() {
    val previewViewModel = BerandaViewModel()
    Row {
        BerandaScreen(viewModel = previewViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBayar() {
    HalamanBayar()
}


@Preview(showBackground = true)
@Composable
fun RekomendasiPreview() {
    Rekomendasi()
}

@Preview(showBackground = true)
@Composable
fun KategoriPreviewDua() {
    Kategori()
}

@Preview(showBackground = true)
@Composable
fun PreviewPencarian() {
    Pencarian()
}

@Preview(showBackground = true)
@Composable
fun PreviewHasilPencarian() {
    SearchResultScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewDetail() {
    DetailProduk(onAddToCartClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    MetodePembayaran()
}

@Preview(showBackground = true)
@Composable
fun PreviewKeranjang() {
    val viewModel = ShoppingCartViewModel().apply {
        cartItems = mutableStateListOf(
            CartItemModel("Ikan Tuna", "500g", "Rp 50.000", R.drawable.ikan_nila, 1),
            CartItemModel("Ikan Salmon", "300g", "Rp 75.000", R.drawable.ikan_patin, 2)
        )
    }
    ShoppingCartScreenWithCustomAppBar(viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun PreviewPesananSukses() {
    OrderSummaryScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewNoInternet() {
    NoInternetScreen()
}
