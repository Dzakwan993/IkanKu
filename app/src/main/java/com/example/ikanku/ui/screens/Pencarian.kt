package com.example.ikanku.ui.screens

import TopBarWithBackIcon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.viewmodel.BerandaViewModel

@Composable
fun Pencarian(viewModel: BerandaViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopBarWithBackIcon(

            )
        },
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header text
            Text(
                text = "Rekomendasi untuk anda",
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // List of recommendations
            val recommendations = listOf(
                "Ikan Gurame dengan kualitas terbaik",
                "Ikan hias aquarium",
                "Ikan Hias mas koki"
            )

            // Displaying each recommendation
            recommendations.forEach { recommendation ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* Handle click */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recommendation,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.lihat_detail), // Replace with appropriate icon
                        contentDescription = "Go to details",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PencarianPreview() {
    Pencarian()
}