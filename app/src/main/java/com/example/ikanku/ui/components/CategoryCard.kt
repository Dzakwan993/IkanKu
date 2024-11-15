// CategoryCard.kt
package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.model.Category

import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.R
import com.example.ikanku.ui.components.CartItem


@Composable
fun CategoryCard(category: Category) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp) // Mengubah tinggi menjadi 100dp untuk proporsi yang lebih baik
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier
                    .size(50.dp) // Mengatur ukuran gambar agar lebih menonjol
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.name,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

