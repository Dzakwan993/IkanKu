package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.CartItem
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.viewmodel.ShoppingCartViewModel
import com.example.ikanku.model.CartItemModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreenWithCustomAppBar(viewModel: ShoppingCartViewModel = viewModel()) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Keranjang Belanja", onBackClick = {}) // Using CustomTopAppBar
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1F) // This allows the LazyColumn to take up available space
            ) {
                itemsIndexed(viewModel.cartItems) { index, item ->
                    CartItem(
                        name = item.name,
                        weight = item.weight,
                        price = item.price,
                        imageRes = item.imageRes,
                        quantity = item.quantity,
                        onIncrease = { viewModel.increaseQuantity(index) },
                        onDecrease = { viewModel.decreaseQuantity(index) }
                    )
                }
            }

            Button(
                onClick = { /* TODO: Add functionality for buying */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
            ) {
                Text("Beli Sekarang", color = Color.White, fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingCartScreenWithCustomAppBar() {
    val viewModel = ShoppingCartViewModel().apply {
        cartItems = mutableStateListOf(
            CartItemModel("Ikan Tuna", "500g", "Rp 50.000", R.drawable.ikan_nila, 1),
            CartItemModel("Ikan Salmon", "300g", "Rp 75.000", R.drawable.ikan_patin, 2)
        )
    }
    ShoppingCartScreenWithCustomAppBar(viewModel = viewModel)
}
