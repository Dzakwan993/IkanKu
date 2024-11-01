package com.yourpackage.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.R
import com.example.ikanku.ui.theme.IkanKuTheme
import com.yourpackage.ikanku.model.Fish
import com.yourpackage.ikanku.ui.components.CategoryCard
import com.yourpackage.ikanku.ui.components.PromoCard
import com.yourpackage.ikanku.ui.components.RecommendationCard
import com.yourpackage.ikanku.viewmodel.BerandaViewModel

@Composable
fun BerandaScreen(
    viewModel: BerandaViewModel = viewModel(),
    recommendations: List<Fish> = viewModel.recommendations.collectAsState().value
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        GreetingSection()
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()

        Text("Semua kategori", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        CategoryRow()

        PromoBanner()

        RecommendationsSection(recommendations)
    }
}

@Composable
fun GreetingSection() {
    Text("Hallo, fazra!", fontWeight = FontWeight.Bold, fontSize = 18.sp)
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Telusuri") },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun CategoryRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(5) { index ->
            CategoryCard(categoryName = "Category $index", imageResId = R.drawable.ikan_patin)
        }
    }
}

@Composable
fun PromoBanner() {
    PromoCard(discountText = "Diskon 50%", imageResId = R.drawable.ikan_patin)
}

@Composable
fun RecommendationsSection(recommendations: List<Fish>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(recommendations) { fish ->
            RecommendationCard(
                fishName = fish.name,
                price = fish.price,
                rating = fish.rating,
                imageResId = fish.imageResId
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewBerandaScreen() {
    val dummyRecommendations = listOf(
        Fish("Ikan Discus", "Rp. 100.000", 4.5f, R.drawable.ikan_patin),
        Fish("Ikan Cupang", "Rp. 40.000", 4.5f, R.drawable.ikan_patin),
        Fish("Ikan Guppy", "Rp. 60.000", 4.2f, R.drawable.ikan_patin)
    )

    IkanKuTheme {
        BerandaScreen(recommendations = dummyRecommendations)
    }
}
