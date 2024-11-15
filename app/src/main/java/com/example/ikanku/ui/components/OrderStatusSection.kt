package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
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
import com.example.ikanku.R
import com.example.ikanku.model.OrderStatusItem

@Composable
fun OrderStatusSection(orderStatusItems: List<OrderStatusItem>, counts: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        orderStatusItems.zip(counts).forEach { (item, count) ->
            OrderStatusCard(item, count, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun OrderStatusCard(orderStatusItem: OrderStatusItem, count: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = orderStatusItem.iconRes),
                    contentDescription = orderStatusItem.label,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = orderStatusItem.label,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }

        // Indicator positioned slightly outside the Card to prevent it from being clipped
        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 8.dp, y = (-8).dp) // Offset to move it outside the card
            ) {
                Card(
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(containerColor = Color.Red),
                    modifier = Modifier
                        .padding(2.dp) // Ensure some spacing for clarity
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
fun OrderStatusRowPreview() {
    val sampleStatusItems = listOf(
        OrderStatusItem("Belum Bayar", R.drawable.belum_bayar),
        OrderStatusItem("Dikemas", R.drawable.dikemas),
        OrderStatusItem("Dikirim", R.drawable.dikirim),
        OrderStatusItem("Selesai", R.drawable.pesanan_selesai),
        OrderStatusItem("Ditolak", R.drawable.pesanan_ditolak)
    )
    val sampleCounts = listOf(3, 0, 5, 1, 2) // Example counts
    OrderStatusSection(orderStatusItems = sampleStatusItems, counts = sampleCounts)
}
