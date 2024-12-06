//package com.example.ikanku.ui.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.ikanku.ui.components.PesananCard
//
//
//@Composable
//fun OrdersScreen(
//    title: String, // Judul layar, misalnya "Pesanan Saya"
//    selectedTab: Int, // Tab yang sedang dipilih
//    onTabSelected: (Int) -> Unit, // Aksi saat tab dipilih
//    orders: List<Order>, // Daftar pesanan dari API
//    buttonBiruText: String, // Teks tombol biru
//    buttonBiruColor: Color = Color(0xFF177BCD), // Warna tombol biru
//    isButtonBiruOutlined: Boolean = false, // Tombol biru bergaya outline atau tidak
//    buttonMerahText: String? = null, // Teks tombol merah (opsional)
//    onClickMerah: ((Order) -> Unit)? = null, // Aksi tombol merah (opsional)
//    onClickBiru: (Order) -> Unit // Aksi tombol biru
//) {
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .navigationBarsPadding(),
//        topBar = {
//            Column {
//                // TopAppBar kustom
//                CustomTopAppBar(
//                    title = title,
//                    onBackClick = { /* Handle back navigation */ }
//                )
//                // Tab navigasi
//                OrderStatusTabs(selectedTab = selectedTab, onTabSelected = onTabSelected)
//            }
//        }
//    ) { innerPadding ->
//        // Daftar pesanan
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(orders) { order ->
//                PesananCard(
//                    order = order,
//                    buttonBiruText = buttonBiruText,
//                    buttonMerahText = buttonMerahText,
//                    onClickBiru = { onClickBiru(order) },
//                    onClickMerah = onClickMerah?.let { { it(order) } }
//                )
//            }
//        }
//    }
//}
