package com.example.ikanku.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.ui.screens.Harga

import com.example.ikanku.ui.screens.OrderSummaryScreen


@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    iconSize: Dp = 24.dp // Parameter baru untuk ukuran bintang
) {
        Row(
            modifier = modifier
        ) {
            repeat(5) { index ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (index < rating) Color(0xFFFFD700) else Color(0xFF9095A0),
                    modifier = modifier
                        .size(iconSize)
                        .clickable { onRatingChanged(index + 1) } // Menambah fungsi klik
                )
            }
        }

}




@Preview(showBackground = true)
@Composable
fun StarPreview() {
    var rating by remember { mutableStateOf(0) }

    Column {
        StarRating(
            rating = rating,
            onRatingChanged = { newRating -> rating = newRating }
        )
    }

}
