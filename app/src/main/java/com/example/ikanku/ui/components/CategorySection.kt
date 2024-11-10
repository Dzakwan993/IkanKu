// CategorySection.kt
package com.example.ikanku.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.model.Category

@Composable
fun CategorySection(categories: List<Category>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Semua kategori",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(categories.size) { index ->
                CategoryCard(category = categories[index]) // Memanggil `CategoryCard`
            }
        }
    }
}
