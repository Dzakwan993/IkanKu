package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.viewmodel.RatingViewModel

@Composable
fun RatingBreakdownScreen(
    viewModel: RatingViewModel = viewModel()
) {
    val ratingData = viewModel.ratingBreakdown.collectAsState().value

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Bagian kiri: Rating rata-rata dan ulasan
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Average rating
                Text(
                    text = "${ratingData.averageRating}/5",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                // Jumlah ulasan
                Text(
                    text = "(${ratingData.totalReviews} Ulasan)",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                // Star rating
                Row {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            tint = if (index < ratingData.averageRating.toInt()) Color(0xFFFFD700) else Color.LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Bagian kanan: Progress bar untuk breakdown
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ratingData.starsCount.reversed().forEachIndexed { index, count ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Angka rating (5, 4, 3, dst.)
                        Text(
                            text = "${5 - index}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Progress bar
                        LinearProgressIndicator(
                            progress = count / ratingData.totalReviews.toFloat(),
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp),
                            color = Color.Black,
                            trackColor = Color.LightGray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Jumlah ulasan
                        Text(
                            text = "$count",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRatingBreakdownScreen() {
    RatingBreakdownScreen()
}
