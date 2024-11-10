package com.example.ikanku.ui.screens

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.BottomNavBar

import com.example.ikanku.ui.components.CategorySection
import com.example.ikanku.ui.components.PromotionSection
import com.example.ikanku.ui.components.RecommendationSection
import com.example.ikanku.ui.components.ProductListSection
import com.example.ikanku.viewmodel.BerandaViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ikanku.ui.components.PromotionSectionRowCarousel

@Composable
fun BerandaScreen(viewModel: BerandaViewModel = viewModel()) { // Ubah Modifier menjadi BerandaViewModel
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = { TopBar() }, // Menjadikan TopBar sticky di bagian atas
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
            item { CategorySection(categories = viewModel.categories) }
            item { PromotionSection(promotions = viewModel.promotions) }
            item { PromotionSectionRowCarousel(smallPromotions = viewModel.smallPromotions) }
            item { RecommendationSection(recommendations = viewModel.recommendations) }
            item { ProductListSection(products = viewModel.products) }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BerandaScreenPreview() {
    val previewViewModel = BerandaViewModel()
    BerandaScreen(viewModel = previewViewModel)
}

