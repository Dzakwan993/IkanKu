package com.example.ikanku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderStatus
import com.example.ikanku.viewmodel.OrderViewModel
@Composable
fun OrderCard(order: Order, onCancelClick: () -> Unit, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable {
                navController.navigate("detail_pesanan")
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
                .padding(top = 4.dp)
        ) {
            Image(
                painter = painterResource(id = order.imageRes),
                contentDescription = order.productName,
                modifier = Modifier
                    .size(64.dp) // Konsisten dengan DikemasCard
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = order.productName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Pilih Variasi Berat: ${order.variant}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = order.price,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onCancelClick,
                modifier = Modifier
                    .width(170.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Batal", fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(
                onClick = { /* Aksi Dikemas */ },
                modifier = Modifier
                    .width(170.dp) // Tetap mempertahankan parameter asli
                    .height(40.dp), // Tetap mempertahankan parameter asli
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent, // Latar belakang transparan
                    contentColor = Color.Black // Warna teks
                ),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, Color.Black) // Garis tepi hitam
            ) {
                Text(text = "Menunggu", fontSize = 15.sp, color = Color.Black)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OrderCardPreview() {
    // Membuat instance ViewModel untuk preview
    val previewViewModel = remember { OrderViewModel() }
    val order = previewViewModel.orders.firstOrNull() // Ambil elemen pertama dari list
    val navController = rememberNavController()

    if (order != null) {
        OrderCard(
            order = order,
            navController = navController,
            onCancelClick = { /* Tambahkan aksi pembatalan untuk preview */ }
        )
    } else {
        Text("Tidak ada pesanan untuk ditampilkan", modifier = Modifier.padding(16.dp))
    }
}
