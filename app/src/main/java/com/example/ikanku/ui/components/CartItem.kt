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
    onDecrease: () -> Unit
) {
    // Remember checked state
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE6EAF9), shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox on the left side
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Product Image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .border(1.dp, Color.LightGray, shape = CircleShape)
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Product Details
        Column(modifier = Modifier.weight(1f)) {
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

            Text(quantity.toString(), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 8.dp))

            IconButton(onClick = onIncrease) {
                Icon(
                    painter = painterResource(id = R.drawable.tambah), // Replace with plus icon resource
                    contentDescription = "Increase quantity"
                )
            }
        }
    }
}

