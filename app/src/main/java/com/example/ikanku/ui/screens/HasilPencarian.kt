package com.example.ikanku.ui.screens

import TopBarWithBackIcon
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.ProductListWithoutLiatLebihSection
import com.example.ikanku.viewmodel.BerandaViewModel

@Composable
fun SearchResultScreen(
    navController: NavController,
    query: String,
    viewModel: BerandaViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopBarWithBackIcon(navController = navController)
        },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Text(
                text = "Hasil Pencarian untuk: \"$query\"",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
            ProductListWithoutLiatLebihSection(products = viewModel.products, navController = navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchResultScreenPreview() {
    val navController = rememberNavController()
    SearchResultScreen(navController = navController, query = "Sample Query")
}