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

import com.example.ikanku.ui.components.CategorySection
import com.example.ikanku.ui.components.RecommendationSection
import com.example.ikanku.ui.components.ProductListSection
import com.example.ikanku.viewmodel.BerandaViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BerandaScreen(navController: NavController, viewModel: BerandaViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            ,
        topBar = { TopBar(navController = navController) },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding())
        ) {
            item { CategorySection(categories = viewModel.categories, navController = navController) }
            item { PromotionSection(promotions = viewModel.promotions, navController = navController) }
            item { RecommendationSection(recommendations = viewModel.recommendations, navController = navController) }
            item { ProductListSection(products = viewModel.products, navController = navController) }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun BerandaScreenPreview() {
    val navController = rememberNavController()
    val previewViewModel = BerandaViewModel()
    BerandaScreen(viewModel = previewViewModel, navController = navController)
}

