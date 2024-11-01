package com.yourpackage.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.yourpackage.ikanku.model.Fish

@Composable
fun RecommendationCard(fishName: String, price: String, rating: Float, imageResId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(painter = painterResource(id = imageResId), contentDescription = fishName)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(fishName, fontWeight = FontWeight.Bold)
                Text(price)
                // Tambahkan rating, dsb.
            }
        }
    }
}
