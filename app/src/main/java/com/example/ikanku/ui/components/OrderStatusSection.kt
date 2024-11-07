package com.example.ikanku.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R // Pastikan import ini benar
import com.example.ikanku.model.OrderStatusItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults

@Composable
fun OrderStatusSection(orderStatusItems: List<OrderStatusItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        orderStatusItems.forEach { item ->
            OrderStatusCard(item)
        }
    }
}

@Composable
fun OrderStatusCard(orderStatusItem: OrderStatusItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(width = 80.dp, height = 100.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)) // Gunakan colors untuk warna latar
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = orderStatusItem.iconRes),
                contentDescription = orderStatusItem.label,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = orderStatusItem.label,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderStatusSectionPreview() {
    val sampleStatusItems = listOf(
        OrderStatusItem("Belum Bayar", R.drawable.beranda),
        OrderStatusItem("Dikemas", R.drawable.beranda),
        OrderStatusItem("Dikirim", R.drawable.beranda),
        OrderStatusItem("Selesai", R.drawable.beranda)
    )
    OrderStatusSection(orderStatusItems = sampleStatusItems)
}
