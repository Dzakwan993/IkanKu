package com.example.ikanku.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OrderStatusTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    navController: NavController
) {
    val tabs = listOf(
        "pesanan" to "pesanan_screen",
        "Dikemas" to "dikemas_screen",
        "Dikirim" to "dikirim_screen",
        "Selesai" to "selesai_screen",
        "Ditolak" to "ditolak_screen"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, (title, route) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable {
                        onTabSelected(index)
                        navController.navigate(route) // Navigasi ke halaman sesuai rute
                    }
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = if (index == selectedTab) FontWeight.Bold else FontWeight.Normal,
                    color = if (index == selectedTab) Color.Black else Color.Gray
                )
                if (index == selectedTab) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(2.dp)
                            .background(Color.Black)
                    )
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun OrderStatusTabsPreview() {
    // Simulasi NavHostController
    val navController = rememberNavController()

    OrderStatusTabs(
        selectedTab = 0,
        onTabSelected = { /* Handle tab selection */ },
        navController = navController
    )
}



