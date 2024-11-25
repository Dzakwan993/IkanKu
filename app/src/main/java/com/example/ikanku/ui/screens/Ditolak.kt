package com.example.ikanku.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.PesananCard
import com.example.ikanku.ui.components.OrderStatusTabs
import com.example.ikanku.viewmodel.OrderRejectedViewModel
import com.example.ikanku.viewmodel.OrderRejectedViewModelDua

@Composable
fun RejectedOrdersScreen(viewModel: OrderRejectedViewModelDua = viewModel()) {
    val rejectedOrders by viewModel.rejectedOrders.collectAsState()
    if (rejectedOrders.isEmpty()) {
        Text(text = "Tidak ada Pesanan yang ")
    } else {
        // Display the orders
        LazyColumn {
            items(rejectedOrders) { order ->
                Text(text = order.namaProduk) // Misalnya nama pesanan
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "Pesanan Saya",
                    onBackClick = { /* Handle back navigation */ }
                )
                OrderStatusTabs(selectedTab = 4, onTabSelected = { /* Aksi untuk memilih tab */ })
            }

        },


    )
     { innerPadding ->
         LazyColumn(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(innerPadding)
                 .padding(16.dp),
             verticalArrangement = Arrangement.spacedBy(8.dp)
         ) {
             if (rejectedOrders.isEmpty()) {
                 item {
                     Text(
                         text = "Tidak ada pesanan yang ditolak",
                         modifier = Modifier.fillMaxWidth(),
                         textAlign = TextAlign.Center
                     )
                 }
             } else {
                 items(rejectedOrders) { order ->
                     PesananCard(
                         order = order,
                         buttonBiruText = "Lihat Alasan",
                         onClickBiru = { /* Handle click */ }
                     )
                 }
             }
         }
     }
}

//@Composable
//@Preview(showBackground = true, )
//fun RejectedOrdersScreenPreview() {
//    val previewViewModel = OrderRejectedViewModel() // ViewModel khusus pesanan ditolak
//    RejectedOrdersScreen(viewModel = previewViewModel)
//}
