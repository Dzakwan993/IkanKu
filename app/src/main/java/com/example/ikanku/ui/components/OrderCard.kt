package com.example.ikanku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderStatus
import com.example.ikanku.viewmodel.OrderViewModel

@Composable
fun OrderCard(order: Order) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    )  {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = order.imageRes),
                contentDescription = order.productName,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = order.productName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = order.variant,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }

                    Text(
                        text = "X${order.quantity}",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Text(
                    text = order.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                when (order.status) {
                    OrderStatus.PAYMENT_REQUIRED -> {
                        Text(
                            text = "Pesanan sudah diterima, silahkan lakukan pembayaran*",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = { /* Handle payment action */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                                modifier = Modifier
                                    .width(199.dp)
                                    .height(36.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text("Bayar Sekarang", color = Color.White, fontSize = 14.sp)
                            }
                        }
                    }
                    OrderStatus.REJECTED -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.End // Aligning button to the end
                        ) {
                            Button(
                                onClick = { /* Handle rejection reason action */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                                modifier = Modifier
                                    .width(199.dp)
                                    .height(36.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text("Ditolak, lihat alasan", color = Color.White, fontSize = 14.sp)
                            }
                        }
                    }
                    else -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            OutlinedButton(
                                onClick = { /* Tidak ada aksi, karena Button dinonaktifkan */ },
                                enabled = false,
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    disabledContainerColor = Color.White,
                                    disabledContentColor = Color.Black
                                ),
                                border = BorderStroke(2.dp, Color.Black),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .width(199.dp)
                                    .height(36.dp)
                            ) {
                                Text("Menunggu Konfirmasi", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}





@Composable
@Preview(showBackground = true)
fun OrderCardPreview() {
    // Membuat instance ViewModel untuk preview
    val previewViewModel = remember { OrderViewModel() }
    val orders = previewViewModel.orders

    Column(modifier = Modifier.padding(16.dp)) {
        orders.forEach { order ->
            OrderCard(order = order)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}