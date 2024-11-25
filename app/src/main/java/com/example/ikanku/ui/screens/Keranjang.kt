package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.CartItem
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.viewmodel.ShoppingCartViewModel
import com.example.ikanku.model.CartItemModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreenWithCustomAppBar(
    navController: NavController,
    viewModel: ShoppingCartViewModel = viewModel()
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedCartItem by remember { mutableStateOf<CartItemModel?>(null) }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Keranjang", onBackClick = { navController.popBackStack() })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1F)
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
                        onDelete = {
                            selectedCartItem = item
                            isBottomSheetVisible = true
                        }
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate("halaman_bayar")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
            ) {
                Text("Selanjutnya", color = Color.White, fontSize = 16.sp)
            }
        }

        if (isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetVisible = false },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.peringatan_pembatalan),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Apakah anda yakin untuk menghapus \"${selectedCartItem?.name}\" dari keranjang?",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                selectedCartItem?.let { item ->
                                    viewModel.removeItem(viewModel.cartItems.indexOf(item))
                                }
                                isBottomSheetVisible = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238))
                        ) {
                            Text("Ya", color = Color.White)
                        }
                        OutlinedButton(onClick = { isBottomSheetVisible = false }) {
                            Text("Tidak")
                        }
                    }
                }
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

    val navController = rememberNavController()
    ShoppingCartScreenWithCustomAppBar(viewModel = viewModel, navController = navController)
}
