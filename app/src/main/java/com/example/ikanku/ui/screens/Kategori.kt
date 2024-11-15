package com.example.ikanku.ui.screens




import PromotionSection
import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.BottomNavBar



import com.example.ikanku.ui.components.RecommendationSection
import com.example.ikanku.ui.components.ProductListSection
import com.example.ikanku.viewmodel.BerandaViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CategoryClickSection
import com.example.ikanku.ui.components.CategorySection
import com.example.ikanku.ui.components.PromotionSectionRowCarousel


@Composable
fun Kategori(viewModel: BerandaViewModel = viewModel(), navController: NavController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = { TopBar(navController = navController) }, // Menjadikan TopBar sticky di bagian atas
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding())
        ) {
            item { CategoryClickSection(categories = viewModel.categories, navController = navController) }
            item { RecommendationSection(recommendations = viewModel.recommendations) }
            item { ProductListSection(products = viewModel.products) }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun KategoriPreview() {
    val navController = rememberNavController()
    val previewViewModel = BerandaViewModel()
    Kategori(viewModel = previewViewModel, navController)
}

