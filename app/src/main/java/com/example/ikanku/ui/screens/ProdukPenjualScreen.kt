package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.viewmodel.ProductViewModel
import com.example.ikanku.ui.components.BottomNavBarPenjual
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.TopBarHanyaJudul


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(setOf<String>()) }
    var selectedTimeFilter by remember { mutableStateOf(setOf<String>()) }
    var isFilterApplied by remember { mutableStateOf(false) }

    // State untuk mengontrol apakah "Lihat Semua" diklik
    var showAllProducts by remember { mutableStateOf(false) }

    // Produk yang akan ditampilkan (6 produk pertama atau semua produk tergantung showAllProducts)
    val productsToShow = if (showAllProducts) viewModel.products else viewModel.products.take(6)

    Scaffold(
        topBar = {
            TopBarHanyaJudul(title = "Produk") {}
        },
        bottomBar = { BottomNavBarPenjual(navController = navController) }
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
                Text("Tambah produk baru", color = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.tambah_produk_penjual),
                    contentDescription = "Add icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable{navController.navigate("tambah_produk_penjual")}
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
                        Text("Telusuri", color = Color.Gray, fontSize = 14.sp)
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .background(color = Color(0xFFE8E8E8), shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Filter dropdown
            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text("Filter berdasarkan kategori", modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
                    val categories = listOf("Ikan Tawar", "Ikan Laut", "Udang", "Kepiting")
                    categories.forEach { category ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedCategory.contains(category),
                                onCheckedChange = {
                                    if (it) {
                                        selectedCategory = selectedCategory + category
                                    } else {
                                        selectedCategory = selectedCategory - category
                                    }
                                }
                            )
                            Text(category, modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Filter berdasarkan waktu", modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
                    val timeFilters = listOf("1 Hari", "1 Minggu", "2 Minggu", "1 Bulan")
                    timeFilters.forEach { time ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedTimeFilter.contains(time),
                                onCheckedChange = {
                                    if (it) {
                                        selectedTimeFilter = selectedTimeFilter + time
                                    } else {
                                        selectedTimeFilter = selectedTimeFilter - time
                                    }
                                }
                            )
                            Text(time, modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            isFilterApplied = true
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Terapkan", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Daftar produk
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Daftar produk", fontWeight = FontWeight.SemiBold)
                        Text("Lihat semua", color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable {
                            showAllProducts = true
                        })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(productsToShow.size) { index ->
                    val product = productsToShow[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                            Spacer(modifier = Modifier.width(15.dp))
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(bottom = 4.dp)
                            ) {
                                Text(
                                    product.name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(product.price, fontSize = 14.sp, color = Color.Gray)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = { navController.navigate("detail_produk_penjual")}) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }

                                // Rating bintang
                                Row(horizontalArrangement = Arrangement.Center) {
                                    val filledStars = 3
                                    val emptyStars = 5 - filledStars

                                    repeat(filledStars) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.star_fill),
                                            contentDescription = "Star",
                                            tint = Color.Yellow,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }

                                    repeat(emptyStars) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.star_empty),
                                            contentDescription = "Empty Star",
                                            tint = Color.Gray,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Tombol Lihat Semua
                    if (!showAllProducts) {
                        Button(
                            onClick = { showAllProducts = true },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Lihat semua", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    ProductScreen(viewModel = viewModel<ProductViewModel>(), navController = rememberNavController())
}
