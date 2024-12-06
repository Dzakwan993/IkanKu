package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.model.Order
import com.example.ikanku.ui.screens.PesananBaru
import com.example.ikanku.viewmodel.OrderRejectedViewModel

@Composable
fun OrderCardDitolak(order: Order) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
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
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
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

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = order.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Display the "Ditolak, Lihat Alasan" button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { /* Handle see reason action */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("Ditolak, Lihat Alasan", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, )
fun PreviewOrderCard() {
    val previewViewModel = OrderRejectedViewModel()
    val sampleOrder = previewViewModel.rejectedOrders.firstOrNull() ?: return
    OrderCardDitolak(order = sampleOrder)
}
