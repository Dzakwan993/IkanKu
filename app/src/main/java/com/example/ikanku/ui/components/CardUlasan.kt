package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.R // Ensure R is imported to access drawable resources
import com.example.ikanku.model.Ulasan
import com.example.ikanku.viewmodel.UlasanViewModel

@Composable
fun CardUlasan(ulasan: Ulasan) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Foto profil pengguna - Load from drawable resource
            Image(
                painter = painterResource(id = ulasan.profilePictureResId), // Use drawable resource ID
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                // Nama pengguna
                Text(
                    text = ulasan.username,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                // Ulasan pengguna
                Text(
                    text = ulasan.review,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Rating bintang
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = if (index < ulasan.rating.toInt()) Color(0xFFFFD700) else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardUlasanList(viewModel: UlasanViewModel = viewModel()) {
    val ulasanList = viewModel.ulasan.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ulasanList.forEach { ulasan ->
            CardUlasan(ulasan = ulasan)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardUlasanList() {
    CardUlasanList()
}
