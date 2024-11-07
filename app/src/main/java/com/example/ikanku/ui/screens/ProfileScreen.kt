package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
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
import com.example.ikanku.ui.components.TopBarWithCart
import com.example.ikanku.ui.components.ProfileCard
import com.example.ikanku.ui.components.OrderStatusSection
import com.example.ikanku.ui.components.BottomNavBar
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
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // ProfileCard di bawah TopBarWithCart
            ProfileCard(
                profile = viewModel.profile,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 40.dp) // Geser ke bawah untuk menumpuk di bawah TopBarWithCart
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 160.dp) // Sesuaikan padding agar konten di bawah ProfileCard
            ) {
                // Teks "Pesanan Saya" dan "Lihat Rincian Pesanan"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Pesanan Saya",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Lihat Rincian Pesanan >",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OrderStatusSection(orderStatusItems = viewModel.orderStatusItems)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val viewModel = viewModel<ProfileViewModel>()
    ProfileScreen(viewModel = viewModel)
}
