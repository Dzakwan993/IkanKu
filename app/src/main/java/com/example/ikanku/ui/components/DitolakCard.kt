package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.model.Order
import com.example.ikanku.ui.screens.PesananBaru
import com.example.ikanku.viewmodel.OrderRejectedViewModel

@Composable
fun OrderCardDitolak(
    navController: NavController,
    order: Order) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
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
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Piih Variasi Berat: ${order.variant}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 8.dp)
                        ,

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

                // Display the "Ditolak, Lihat Alasan" button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Button(
                        onClick = { navController.navigate("alasan_ditolak") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.width(180.dp)

                    ) {
                        Text(
                            "Ditolak, Lihat Alasan", color = Color.White,

                        )
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
    val navController = rememberNavController()
    OrderCardDitolak(order = sampleOrder, navController = navController)
}
