// CategorySection.kt
package com.example.ikanku.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ikanku.R
import com.example.ikanku.model.Category

@Composable
fun CategorySection(categories: List<Category>, navController: NavController) {
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
                CategoryCard(category = categories[index], navController = navController) // Memanggil `CategoryCard`
            }
        }
    }
}

@Composable
fun CategoryClickSection(categories: List<Category>, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back Icon",
                tint = Color.Black,
                modifier = Modifier.clickable{navController.popBackStack()}
            )
            Text(
                text = "Ikan Tawar",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(categories.size) { index ->
                CategoryCard(category = categories[index], navController = navController) // Memanggil `CategoryCard`
            }
        }
    }
}
