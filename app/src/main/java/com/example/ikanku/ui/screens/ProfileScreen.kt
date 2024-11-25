package com.example.ikanku.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.TopBarWithCart
import com.example.ikanku.ui.components.ProfileCard
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.OrderStatusSection
import com.example.ikanku.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel(), navController: NavController) {
    var isBottomSheetVisible by remember { mutableStateOf(false) } // State untuk BottomSheet

    Scaffold(
        topBar = {
            TopBarWithCart(
                title = "Profile",
                onBackClick = { navController.popBackStack()},
                onCartClick = { /* Handle cart click */ },
                navController = navController
            )
        },
        bottomBar = { BottomNavBar(navController = navController) }
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
                    .offset(y = 16.dp),
                navController = navController
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 140.dp, start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState()),
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
                    counts = viewModel.counts,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Menu options
                ProfileOptionCard(
                    text = "Ganti password",
                    onClick = { navController.navigate("change_password_screen") }
                )
                ProfileOptionCard(
                    text = "Ubah Email",
                    onClick = { navController.navigate("ubah_email") }
                )
                ProfileOptionCard(
                    text = "Alamat",
                    onClick = { navController.navigate("alamat") }
                )
                ProfileOptionCard(
                    text = "Pusat bantuan",
                    onClick = { navController.navigate("pusat_bantuan") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Logout button
                OutlinedButton(
                    onClick = { isBottomSheetVisible = true }, // Tampilkan BottomSheet
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

        // BottomSheet untuk konfirmasi logout
        if (isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetVisible = false },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.peringatan_pembatalan),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "Apakah Anda yakin ingin logout?",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                // Tambahkan logika logout di sini
                                isBottomSheetVisible = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238))
                        ) {
                            Text("Ya", color = Color.White)
                        }
                        OutlinedButton(
                            onClick = { isBottomSheetVisible = false }
                        ) {
                            Text("Tidak")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProfileOptionCard(text: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(onClick = onClick) // Tambahkan klik navigasi
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}













@Preview(showBackground = true)
@Composable
fun PreviewDikirim() {
    val navController = rememberNavController()
    DikirimScreen(
        onBackClick = {},
        onDeliveryClick = {},
        navController = navController

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailPengirimann() {
    val navController = rememberNavController()
    DetailPengiriman(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewSelesai() {
    val navController = rememberNavController()
    SelesaiScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewDitolak() {
    val navController = rememberNavController()
    RejectedOrdersScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewUlasann() {
    val navController = rememberNavController()
    Ulasan(navController = navController)
}


