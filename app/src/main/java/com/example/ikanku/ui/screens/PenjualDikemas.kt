package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.CardDuaPilihan
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.PenjualStatusTabs
import com.example.ikanku.viewmodel.OrderRejectedViewModel


@Composable
fun PenjualDikemas(viewModel: OrderRejectedViewModel = viewModel()) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "Pesanan",
                    onBackClick = { /* Handle back navigation */ }
                )
                PenjualStatusTabs(selectedTab = 1, onTabSelected = { /* Aksi untuk memilih tab */ })
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

            items(viewModel.rejectedOrders,
            ) { order ->
                CardDuaPilihan(
                    order,
                    pilihanBiru = "Terima",
                    pilihanMerah = "Tolak"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, )
fun PenjualDikemasuPreview() {
    val previewViewModel = OrderRejectedViewModel() // ViewModel khusus pesanan ditolak
    PenjualDikemas(viewModel = previewViewModel)
}