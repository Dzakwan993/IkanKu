package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.model.ProfilPenjual
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfilPenjualCard(profilPenjual: ProfilPenjual, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Increase this for a stronger shadow effect
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = profilPenjual.imageRes),
                contentDescription = "Gambar Profil Penjual",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = profilPenjual.name, fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Aksi pengaturan */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.pensil),
                    contentDescription = "Ikon Pengaturan",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilPenjualCard() {
    val sampleProfilPenjual = ProfilPenjual(
        name = "Tibelat Farm",
        imageRes = R.drawable.tibelat_farm // Replace with actual drawable resource
    )

    ProfilPenjualCard(profilPenjual = sampleProfilPenjual)
}
