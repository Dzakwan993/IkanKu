package com.example.ikanku.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ikanku.model.Product

@Composable
fun ProductListSection(products: List<Product>, navController: NavController) {
    var showAllProducts by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val productsToShow = if (showAllProducts) products else products.take(3)
            productsToShow.forEach { product ->
                ProductItemCard(product = product, navController = navController)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (!showAllProducts && products.size > 3) {
            Button(
                onClick = { showAllProducts = true },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Lihat Lebih", color = Color.Gray)
            }
        }
    }
}

@Composable
fun ProductListWithoutLiatLebihSection(products: List<Product>, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            products.forEach { product ->
                ProductItemCard(product = product, navController = navController)
            }
        }
    }
}
