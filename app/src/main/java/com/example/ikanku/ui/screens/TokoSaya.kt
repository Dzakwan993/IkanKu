package com.example.ikanku.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.OrderStatusItem
import com.example.ikanku.model.ProfilPenjual
import com.example.ikanku.ui.components.*
import com.example.ikanku.utils.SharedPreferencesHelper


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokoSayaScreen(
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    navController: NavController
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) } // State to show the BottomSheet for logout confirmation
    val context = LocalContext.current // Mendapatkan context dari LocalContext
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Custom AppBar with back and cart icons
        TopBarHanyaJudul(title = "Toko Saya", )

        // Profil Penjual Card
        ProfilPenjualCard(
            profilPenjual = ProfilPenjual(
                name = "Tibelat Farm",
                imageRes = R.drawable.tibelat_farm // Use appropriate resource for profile image
            ),
            modifier = Modifier.padding(vertical = 16.dp),
            navController = navController
        )

        // Row for Sales Status and more
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sales Status Text
            Text(
                text = "Status Penjualan",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            // Additional details on the right side
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Lainnya >",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        navController.navigate("pesanan_screen_penjual")
                    }
                )
            }
        }

        // Order Status Cards
        OrderStatusPenjual(
            orderStatusItems = listOf(
                OrderStatusItem("Pesanan", R.drawable.pesanan),
                OrderStatusItem("Dikirim", R.drawable.pesanan_dikirim),
                OrderStatusItem("Pembatalan", R.drawable.pembatalan),
                OrderStatusItem("Selesai", R.drawable.pesanan_selesai)
            ),
            counts = listOf(3, 0, 5, 1), // Example counts for each order status
            cardWidth = 100.dp,
            cardHeight = 120.dp,
            navController = navController
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Cards for Password, Email, and Help Center
        GantiPasswordCard(navController = navController)
        EmailCard(navController = navController)
        PusatBantuanCard(navController = navController)

        // Logout Button
        OutlinedButton(
            onClick = { isBottomSheetVisible = true }, // Show the BottomSheet for logout confirmation
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Red)
        ) {
            Text("Logout", color = Color.Red, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Bar
        BottomNavBarPenjual(navController = navController)
    }

    // BottomSheet for logout confirmation
    // BottomSheet for logout confirmation
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
                // Icon for the confirmation
                Icon(
                    painter = painterResource(id = R.drawable.peringatan_pembatalan),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )

                // Confirmation Text
                Text(
                    text = "Apakah Anda yakin ingin logout?",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Actions: Yes and No buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Yes Button: Logout logic
                    Button(
                        onClick = {
                            SharedPreferencesHelper.logout(context) // Hapus data login dari SharedPreferences
                            navController.navigate("login_screen") {
                                popUpTo("login_screen") { inclusive = true } // Bersihkan stack navigasi
                            }
                            isBottomSheetVisible = false // Tutup BottomSheet
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFFF4238), // Tombol berwarna merah
                            contentColor = Color.White // Teks berwarna putih
                        )
                    ) {
                        Text("Ya", color = Color.White)
                    }

                    // No Button: Close BottomSheet
                    OutlinedButton(
                        onClick = { isBottomSheetVisible = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color.Red)
                    ) {
                        Text("Tidak", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

}


@Composable
fun GantiPasswordCard(navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(8.dp), // Shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable{navController.navigate("change_password_screen")}
    ) {
        Text(
            text = "Ganti Password",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun EmailCard(navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(8.dp), // Shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable{navController.navigate("ganti_email_penjual")}
    ) {
        Text(
            text = "Email",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun PusatBantuanCard(navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(8.dp), // Shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable{navController.navigate("pusat_bantuan")}
    ) {
        Text(
            text = "Pusat Bantuan",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TokoSayaScreenPreview() {
    val navController = rememberNavController()
    TokoSayaScreen(
        onBackClick = { /* Handle back action */ },
        onCartClick = { /* Handle cart action */ },
        navController = navController
    )
}