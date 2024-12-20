package com.example.ikanku.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Recommendation

@Composable
fun RecommendationSection(recommendations: List<Recommendation>, navController: NavController) { // Tambahkan parameter di sini
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rekomendasi untuk anda",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding()

            )

            TextButton(
                onClick = { navController.navigate("daftar_rekomendasi")}
            ) {
                    Text("Lihat semua", color = Color.Black,)
            }

        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
            items(recommendations.size) { index ->
                RecommendationCard(recommendation = recommendations[index], navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecommendationSection() {
    // Dummy data untuk rekomendasi
    val recommendations = listOf(
        Recommendation(
            R.drawable.gambar_rekomen_1,
            "Ikan Discus",
            "Ikan discus dari Amazon",
            "100.000",
            4.5
        ),
        Recommendation(
            R.drawable.gambar_rekomen_2,
            "Ikan Cupang",
            "Ikan lokal yang cantik",
            "40.000",
            4.0
        )
    )

    val navController = rememberNavController() // Dummy NavController untuk preview

    RecommendationSection(recommendations = recommendations, navController = navController)
}

