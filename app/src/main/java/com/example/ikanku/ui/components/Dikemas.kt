package com.example.ikanku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.model.Dikemas
@Composable
fun DikemasCard(
    dikemas: Dikemas,
    onCancelClick: () -> Unit,

    ) {
    Card(
        shape = RoundedCornerShape(16.dp), // Disamakan dengan OrderCardDitolak
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp), // Disamakan dengan OrderCardDitolak
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
                .padding(top = 4.dp) // Konsisten dengan OrderCardDitolak
        ) {
            Image(
                painter = painterResource(id = dikemas.imageResId),
                contentDescription = dikemas.name,
                modifier = Modifier
                    .size(64.dp) // Disamakan dengan OrderCardDitolak
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = dikemas.name,
                    fontSize = 18.sp, // Disamakan
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Disamakan
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Pilih Variasi Berat: ${dikemas.weightVariation}",
                    fontSize = 14.sp, // Disamakan
                    color = Color.DarkGray // Disamakan
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp, // Disamakan
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Disamakan
                    )
                    Text(
                        text = "Rp${dikemas.price}",
                        fontSize = 16.sp, // Disamakan
                        color = Color.Black // Disamakan
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))


            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp),
           horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Button(
                onClick = onCancelClick,
                modifier = Modifier
                    .width(170.dp) // Tetap mempertahankan parameter asli
                    .height(40.dp), // Tetap mempertahankan parameter asli
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(30.dp) // Disamakan dengan OrderCardDitolak
            ) {
                Text(text = "Batal", fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = { /* Aksi Dikemas */ },
                modifier = Modifier
                    .width(170.dp) // Tetap mempertahankan parameter asli
                    .height(40.dp), // Tetap mempertahankan parameter asli
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent, // Latar belakang transparan
                    contentColor = Color.Black // Warna teks
                ),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, Color.Black) // Garis tepi hitam
            ) {
                Text(text = "Dikemas", fontSize = 15.sp, color = Color.Black)
            }
        }


//         COBA TAMBAH SINI
    }
}



@Preview(showBackground = true)
@Composable
fun DikemasCardPreview() {
    // Sample data for preview with a drawable resource
    val sampleDikemas = Dikemas(
        name = "Ikan Nila",
        weightVariation = "1 Kg",
        price = "40.000",
        quantity = 1,
        imageResId = R.drawable.ikan_nila, // Use your drawable resource here
        status = "Pesanan Anda sedang dikemas*"
    )

    DikemasCard(dikemas = sampleDikemas,
        onCancelClick = {}
        )
}
