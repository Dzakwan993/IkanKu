// File: ui/components/CartItem.kt
package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R

@Composable
fun CartItem(
    name: String,
    weight: String,
    price: String,
    imageRes: Int,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Top Row with Checkbox and Trash Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Aligns the content at the ends
            ) {

                Checkbox(
                    checked = false,
                    onCheckedChange = { /* Handle checkbox change */ }
                )

                // Trash Icon
                IconButton(onClick = onDelete) {
                    Icon(
                        painter = painterResource(id = R.drawable.hapus), // Replace with your trash icon resource
                        contentDescription = "Delete item",
                        tint = Color.Gray
                    )
                }
            }

            // Content Row with Image, Text, and Quantity Selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp), // Increased bottom padding only
                verticalAlignment = Alignment.CenterVertically
            )
            {



                // Product Image
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .border(1.dp, Color.LightGray, shape = CircleShape)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Product Details
                Column(modifier = Modifier
                    .weight(1f),
                    horizontalAlignment = Alignment.Start // Aligns the text to the left
                ) {
                    Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(weight, color = Color.Gray, fontSize = 14.sp)
                    Text(price, color = Color.Black, fontSize = 16.sp)
                }


                // Quantity Selector
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onDecrease) {
                        Icon(
                            painter = painterResource(id = R.drawable.kurang), // Replace with minus icon resource
                            contentDescription = "Decrease quantity"
                        )
                    }

                    Text(quantity.toString(), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 4.dp)) // Reduced padding

                    IconButton(onClick = onIncrease) {
                        Icon(
                            painter = painterResource(id = R.drawable.tambah), // Replace with plus icon resource
                            contentDescription = "Increase quantity"
                        )
                    }
                }

            }
        }
    }
}



@Preview
@Composable
private fun PreviewCartItem() {

    CartItem(
        name = "Ikan Nila",
        weight = "500g",
        price = "Rp 20.000",
        imageRes = R.drawable.ikan_nila, // Replace with a sample image resource
        quantity = 1,
        onIncrease = {},
        onDecrease = {},
        onDelete = {}
    )

}



