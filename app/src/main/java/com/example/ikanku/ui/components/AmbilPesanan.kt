package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.viewmodel.OrderAmbilPesananViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.model.OrderAmbilPesananModel
import com.example.ikanku.R

@Composable
fun OrderAmbilPesananItem(
    viewModel: OrderAmbilPesananViewModel = viewModel(),
    buttonWidth: Int = 120,
    buttonHeight: Int = 40,
    fontSize: Int = 14
) {
    val order = viewModel.orderData.value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                // Display image
                val imagePainter: Painter = painterResource(id = R.drawable.ikan_nila)
                Image(
                    painter = imagePainter,
                    contentDescription = order.title,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(50.dp)), // Circular image
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Display details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = order.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Pilih Variasi Berat ${order.weightOption}",
                        fontSize = fontSize.sp,  // Set font size for the details
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Rp${order.price}",
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSize.sp,  // Set font size for the price
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "X${order.quantity}",
                            fontSize = fontSize.sp,  // Set font size for the quantity
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            // New row for buttons, separate from the details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { viewModel.cancelOrder() },
                    modifier = Modifier
                        .width(150.dp)   // Set button width
                        .height(40.dp), // Set button height
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Batal", fontSize = 15.sp) // Set font size for button text
                }
                Button(
                    onClick = { viewModel.markAsPickedUp() },
                    modifier = Modifier
                        .width(150.dp)  // Set button width
                        .height(40.dp), // Set button height
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Siap Diambil", fontSize = 15.sp, color = Color.White) // Set font size for button text
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOrderAmbilPesananItem() {
    val previewViewModel = OrderAmbilPesananViewModel().apply {
        orderData.value = OrderAmbilPesananModel(
            imageUrl = "https://example.com/image.jpg",
            title = "Ikan Nila",
            weightOption = "1 Kg",
            price = "40.000",
            quantity = 1,
            isPickedUp = false
        )
    }
    // You can adjust the button and font size here for preview
    OrderAmbilPesananItem(viewModel = previewViewModel, buttonWidth = 130, buttonHeight = 45, fontSize = 16)
}
