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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.OrderStatusItem

@Composable
fun OrderStatusSection(
    orderStatusItems: List<OrderStatusItem>,
    counts: List<Int>,
    navController: NavController // Tambahkan parameter navController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        orderStatusItems.zip(counts).forEach { (item, count) ->
            OrderStatusCard(
                orderStatusItem = item,
                count = count,
                modifier = Modifier.weight(1f),
                onClick = {
                    // Tambahkan navigasi berdasarkan jenis status
                    when (item.label) {
                        "Pesanan" -> navController.navigate("pesanan_screen")
                        "Dikemas" -> navController.navigate("dikemas_screen") // Ganti dengan route sesuai
                        "Dikirim" -> navController.navigate("dikirim_screen")
                        "Selesai" -> navController.navigate("selesai_screen")
                        "Ditolak" -> navController.navigate("ditolak_screen")
                        else -> {}
                    }
                }
            )
        }
    }
}

@Composable
fun OrderStatusCard(
    orderStatusItem: OrderStatusItem,
    count: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit // Tambahkan parameter onClick
) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable { onClick() }, // Tambahkan klik
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
                    color = Color.Black,
                    maxLines = 1, // Prevent text from being cut off
                    overflow = TextOverflow.Ellipsis, // Display ellipsis if text overflows
                    modifier = Modifier.padding(top = 4.dp) // Add some space between icon and text
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
fun OrderStatusRowPreview() {
    val navController = rememberNavController()
    val sampleStatusItems = listOf(
        OrderStatusItem("Pesanan", R.drawable.pesanan_pembeli),
        OrderStatusItem("Dikemas", R.drawable.dikemas),
        OrderStatusItem("Dikirim", R.drawable.dikirim),
        OrderStatusItem("Selesai", R.drawable.pesanan_selesai),
        OrderStatusItem("Ditolak", R.drawable.pesanan_ditolak)
    )
    val sampleCounts = listOf(3, 0, 5, 1, 2) // Example counts
    OrderStatusSection(orderStatusItems = sampleStatusItems, counts = sampleCounts,navController = navController )
}
