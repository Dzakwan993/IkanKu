package com.example.ikanku.ui.screens

import TopBarWithBackIcon
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.ProductListSection
import com.example.ikanku.ui.components.ProductListWithoutLiatLebihSection
import com.example.ikanku.viewmodel.BerandaViewModel

@Composable
fun SearchResultScreen(viewModel: BerandaViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopBarWithBackIcon() // Menggunakan TopBarWithBackIcon untuk halaman pencarian
        },
        bottomBar = { BottomNavBar() } // Menggunakan BottomNavBar untuk navigasi bawah
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Mengambil daftar produk dari viewModel
            ProductListWithoutLiatLebihSection(products = viewModel.products)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultScreenPreview() {
    SearchResultScreen()
}
