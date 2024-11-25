package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    navigateToShippingMethodScreen: () -> Unit,
    navigateToPaymentMethodScreen: () -> Unit,
    navigateToBankAccountScreen: () -> Unit,
    navigateToShippingCostScreen: () -> Unit // Added navigation for Shipping Cost
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Pengaturan") {
                onBackClick()  // Handle back action
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Fitur Toko Libur
            ToggleCard(
                title = "Fitur Toko Libur",
                initialState = false
            )

            // Metode Pengiriman
            ExpandableCard(
                title = "Metode Pengiriman",
                content = "Tampilkan metode pengiriman yang tersedia",
                onDetailClick = navigateToShippingMethodScreen // Navigate to Shipping Method Screen
            )

            // Metode Pembayaran
            ExpandableCard(
                title = "Metode Pembayaran",
                content = "Tampilkan metode pembayaran yang tersedia",
                onDetailClick = navigateToPaymentMethodScreen // Navigate to Payment Method Screen
            )

            // Rekening Bank/Kartu
            ExpandableCard(
                title = "Rekening Bank/Kartu",
                content = "Tampilkan rekening bank atau kartu yang terhubung",
                onDetailClick = navigateToBankAccountScreen // Navigate to Bank Account Screen
            )

            // Biaya Ongkos Kirim
            ExpandableCard(
                title = "Biaya Ongkos Kirim",
                content = "Tampilkan biaya ongkos kirim yang tersedia",
                onDetailClick = navigateToShippingCostScreen // Navigate to Shipping Cost Screen
            )
        }
    }
}

@Composable
fun ToggleCard(
    title: String,
    initialState: Boolean
) {
    var isActive by remember { mutableStateOf(initialState) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isActive) Color(0xFF177BCD) else Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Toggle action
                Switch(
                    checked = isActive,
                    onCheckedChange = { isActive = it }
                )
            }
        }
    }
}

@Composable
fun ExpandableCard(
    title: String,
    content: String,
    onDetailClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                IconButton(onClick = {
                    isExpanded = !isExpanded
                    onDetailClick() // Trigger navigation or any other action
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.lihat_detail),
                        contentDescription = "Expand",
                        tint = Color.Black
                    )
                }
            }

            // Show content when expanded
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = content,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ShippingMethodScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Metode Pengiriman") {
                onBackClick()
            }
        }
    ) { innerPadding ->
        // Add the form or fields to edit shipping methods here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Edit Metode Pengiriman",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            // Implement form to update shipping methods
        }
    }
}

@Composable
fun BankAccountScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Rekening Bank/Kartu") {
                onBackClick()
            }
        }
    ) { innerPadding ->
        // Add the form or fields to edit bank accounts here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Edit Rekening Bank/Kartu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            // Implement form to update bank accounts
        }
    }
}

@Composable
fun ShippingCostScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Biaya Ongkos Kirim") {
                onBackClick()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Edit Biaya Ongkos Kirim",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            // Implement form to update shipping costs here
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(
        onBackClick = {
            // For preview, just print a message instead of performing navigation
            println("Back clicked")
        },
        navigateToShippingMethodScreen = {
            // For preview, just print a message
            println("Navigating to Shipping Method Screen")
        },
        navigateToPaymentMethodScreen = {
            // For preview, just print a message
            println("Navigating to Payment Method Screen")
        },
        navigateToBankAccountScreen = {
            // For preview, just print a message
            println("Navigating to Bank Account Screen")
        },
        navigateToShippingCostScreen = {
            // For preview, just print a message
            println("Navigating to Shipping Cost Screen")
        }
    )
}

