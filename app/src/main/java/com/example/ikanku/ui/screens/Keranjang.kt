package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreenWithCustomAppBar(
    navController: NavController,
    viewModel: ShoppingCartViewModel = viewModel()) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Keranjang ", onBackClick = { navController.popBackStack()}) // Using CustomTopAppBar
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
                        onDecrease = { viewModel.decreaseQuantity(index) },
                        onDelete = { viewModel.removeItem(index) }
                    )
                }
            }

            TombolMasukkanKeranjang(
                text = "Beli Sekarang",
                onClick = {
                    navController.navigate("halaman_bayar")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShoppingCartScreenWithCustomAppBar() {
    val navController = rememberNavController()
    val viewModel = ShoppingCartViewModel().apply {
        cartItems = mutableStateListOf(
            CartItemModel("Ikan Tuna", "500g", "Rp 50.000", R.drawable.ikan_nila, 1),
            CartItemModel("Ikan Salmon", "300g", "Rp 75.000", R.drawable.ikan_patin, 2)
        )
    }
    ShoppingCartScreenWithCustomAppBar(navController = navController, viewModel = viewModel, )
}
