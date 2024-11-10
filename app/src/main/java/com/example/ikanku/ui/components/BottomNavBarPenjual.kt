package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.ikanku.R

@Composable
fun BottomNavBarPenjual() {
    val items = listOf(
        BottomNavItemPenjual.Home,
        BottomNavItemPenjual.Products,
        BottomNavItemPenjual.Settings
    )

    val poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )

    Box {
        NavigationBar(
            containerColor = Color(0xFF177BCD),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Image(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.label
                        )
                    },
                    label = { Text(item.label, color = Color.White, fontFamily = poppins) },
                    selected = false,
                    onClick = { /* Handle navigation here */ }
                )
            }
        }
    }
}

sealed class BottomNavItemPenjual(val label: String, val iconRes: Int) {
    object Home : BottomNavItemPenjual("Beranda", R.drawable.beranda)
    object Products : BottomNavItemPenjual("Produk", R.drawable.produk_penjual)
    object Settings : BottomNavItemPenjual("Pengaturan", R.drawable.pengaturan_penjual)
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPenjualPreview() {
    BottomNavBarPenjual()
}
