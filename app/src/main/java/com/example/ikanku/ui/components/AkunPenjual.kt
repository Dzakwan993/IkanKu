
package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.R
import com.example.ikanku.viewmodel.ProfilPenjualAkunViewModel

@Composable
fun ProfilPenjualCard(
    profilPenjualViewModel: ProfilPenjualAkunViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    // Observe profilPenjual data from the ViewModel
    val profilPenjual by profilPenjualViewModel.profilAkunPenjual

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = profilPenjual.imageRes),
                contentDescription = "Gambar Profil Penjual",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Seller Name
            Text(
                text = profilPenjual.name,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Edit Icon
            IconButton(onClick = { /* Define edit action here */ }) {
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
fun PreviewProfilPenjualCardAkun() {
    ProfilPenjualCard()
}
