package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.R
import com.example.ikanku.model.Product
import com.example.ikanku.viewmodel.ProductViewModel
import com.example.ikanku.ui.components.BottomNavBarPenjual
import com.example.ikanku.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(viewModel: ProductViewModel = viewModel()) {
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Produk", onBackClick = {})
        },
        bottomBar = { BottomNavBarPenjual() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Tombol tambah produk
            Button(
                onClick = { /* Handle add product click */ },
                modifier = Modifier
                    .width(170.dp)
                    .height(38.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9)),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(start = 16.dp)
            ) {
                Text("Tambah produk", color = Color.Black, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.tambah_produk_penjual),
                    contentDescription = "Add icon",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Pencarian produk dengan ikon filter
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle search text change */ },
                    placeholder = {
                        Text("Telusuri", color = Color.Gray, fontSize = 14.sp) // Warna dan ukuran placeholder
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray,)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp) // Tinggi yang cukup untuk teks placeholder
                        .background(color = Color(0xFFE8E8E8), shape = RoundedCornerShape(16.dp)) // Warna dan bentuk background
                        .clip(RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp) // Rounded shape untuk TextField
                )

                IconButton(
                    onClick = { /* Handle filter click */ },
                    modifier = Modifier
                        .size(48.dp) // Tinggi yang sama dengan TextField agar seimbang
                        // Warna dan bentuk yang sama
                        .clip(RoundedCornerShape(16.dp)) // Bentuk rounded
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter), // Ganti dengan ikon dari drawable
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Judul Daftar Produk
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Daftar produk", fontWeight = FontWeight.SemiBold)
                Text("Lihat semua", color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Daftar produk
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(viewModel.products.size) { index ->
                    val product = viewModel.products[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = product.imageRes),
                                contentDescription = product.name,
                                modifier = Modifier.size(50.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(product.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                Text(product.price, fontSize = 14.sp, color = Color.Gray)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("${product.rating} ★", fontSize = 12.sp, color = Color.Gray)
                                IconButton(onClick = { /* Handle add product to cart */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.tambah),
                                        contentDescription = "Add",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Lihat Semua
            Button(
                onClick = { /* Handle see all products click */ },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Lihat semua", color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    ProductScreen(viewModel = viewModel<ProductViewModel>())
}
