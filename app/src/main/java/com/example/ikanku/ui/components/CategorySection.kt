// CategorySection.kt
package com.example.ikanku.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Category

@Composable
fun CategorySection(categories: List<Category>, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
        .fillMaxWidth() // Pastikan Column mengambil lebar penuh
        ,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Semua kategori",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),


        ) {
            items(categories.size) { index ->
                CategoryCard(category = categories[index], navController = navController) // Memanggil `CategoryCard`
            }
        }
    }
}



@Composable
fun CategoryClickSection(categories: List<Category>, navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
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

@Preview(showBackground = true)
@Composable
fun PreviewCategorySection() {
    // Dummy data untuk preview
    val categories = listOf(
        Category("Hias", R.drawable.kategori_ikanhias),
        Category("Air Tawar", R.drawable.kategori_airtawar),
        Category("Bibit", R.drawable.kategori_bibit)
    )

    val navController = rememberNavController() // Dummy NavController

    // Memanggil fungsi CategorySection dengan data dummy
    CategorySection(categories = categories, navController = navController)
}
