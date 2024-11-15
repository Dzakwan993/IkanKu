package com.example.ikanku.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PenjualStatusTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("Pesanan Masuk", "Dikemas", "Dikirim", "Selesai", "Pembatalan", "Ulasan", "Diarsipkan")
    val scrollState = rememberScrollState() // State untuk scroll

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState) // Membuat scroll horizontal
            .padding(vertical = 8.dp)
            .padding(horizontal = 16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, title ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { onTabSelected(index) }
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
fun PenjualStatusTabsPreview() {
    PenjualStatusTabs(
        selectedTab = 4,
        onTabSelected = {}
    )
}

