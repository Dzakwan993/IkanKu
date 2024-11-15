package com.example.ikanku.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.model.Dikemas
import com.example.ikanku.ui.components.TopBarWithCart
import com.example.ikanku.ui.components.ProfileCard
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.OrderStatusSection
import com.example.ikanku.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopBarWithCart(
                title = "Profile",
                onBackClick = { /* Handle back navigation here */ },
                onCartClick = { /* Handle cart click */ }
            )
        },
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ProfileCard(
                profile = viewModel.profile,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 140.dp, start = 32.dp, end = 32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Pesanan Saya",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // OrderStatusSection with data from ViewModel
                OrderStatusSection(
                    orderStatusItems = viewModel.orderStatusItems,
                    counts = viewModel.counts
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Menu options
                ProfileOptionCard("Ganti password")
                ProfileOptionCard("Email")
                ProfileOptionCard("Alamat")
                ProfileOptionCard("Pusat bantuan")

                Spacer(modifier = Modifier.height(16.dp))

                // Logout button
                OutlinedButton(
                    onClick = { /* Handle logout */ },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Text("Logout", color = Color.Red, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileOptionCard(text: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    DikemasScreen()
}


@Preview(showBackground = true)
@Composable
fun PreviewDikirim() {
    DikirimScreen(
        onBackClick = {},
        onDeliveryClick = {},
        onReorderClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailPengirimann() {
    DetailPengiriman()
}

@Preview(showBackground = true)
@Composable
fun PreviewSelesai() {
    SelesaiScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewDitolak() {
    RejectedOrdersScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewUlasann() {
    Ulasan()
}


