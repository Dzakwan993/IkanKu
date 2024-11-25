package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.OrderStatusItem

@Composable
fun OrderStatusPenjual(
    orderStatusItems: List<OrderStatusItem>,
    counts: List<Int>,
    navController: NavController,  // Add NavController as a parameter
    cardWidth: Dp = 80.dp,
    cardHeight: Dp = 100.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        orderStatusItems.zip(counts).forEach { (item, count) ->
            OrderStatusPenjualCard(item, count, cardWidth, cardHeight, navController, modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun OrderStatusPenjualCard(
    orderStatusItem: OrderStatusItem,
    count: Int,
    cardWidth: Dp,
    cardHeight: Dp,
    navController: NavController,  // Accept NavController
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 2.dp)
            .clickable {
                if (orderStatusItem.label == "Pesanan") { // Check if "Pesanan" is clicked
                    navController.navigate("pesanan_screen_penjual") // Navigate to PesananScreen
                }
            }
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
            ) {
                Image(
                    painter = painterResource(id = orderStatusItem.iconRes),
                    contentDescription = orderStatusItem.label,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = orderStatusItem.label,
                    fontSize = 11.sp,
                    color = Color.Black
                )
            }
        }

        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 8.dp, y = (-8).dp)
            ) {
                Card(
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(containerColor = Color.Red),
                    modifier = Modifier
                        .padding(2.dp)
                        .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
                ) {
                    Text(
                        text = count.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        maxLines = 1
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderStatusPenjualPreview() {
    val sampleStatusItems = listOf(
        OrderStatusItem("Pesanan", R.drawable.pesanan),
        OrderStatusItem("Dikirim", R.drawable.pesanan_dikirim),
        OrderStatusItem("Pembatalan", R.drawable.pembatalan),
        OrderStatusItem("Selesai", R.drawable.pesanan_selesai)
    )
    val sampleCounts = listOf(3, 0, 5, 1)
    val navController = rememberNavController()
    OrderStatusPenjual(
        orderStatusItems = sampleStatusItems,
        counts = sampleCounts,
        cardWidth = 100.dp, // Tentukan lebar manual
        cardHeight = 120.dp,
        navController = navController
    )
}
