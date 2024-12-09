package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.PesananSelesai

@Composable
fun LihatUlasanCard(
    selesai: PesananSelesai,
    onClick: () -> Unit,
    navController: NavController,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
                .padding(top = 4.dp)
        ) {
            // Product image on the left
            Image(
                painter = painterResource(id = selesai.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp) // Gambar sesuai dengan ukuran yang ada di kode pertama
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    fontSize = 18.sp, // Sesuaikan ukuran font untuk judul
                    text = selesai.name,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp)) // Jarak antar teks judul dan deskripsi
                Text(
                    fontSize = 14.sp, // Ukuran font deskripsi sesuai dengan kode pertama
                    text = "Piih Variasi Berat: ${selesai.weightVariation}",
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp)) // Jarak sebelum baris Total

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        fontSize = 16.sp,
                        text = "Rp${selesai.price}",

                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(4.dp)) // Jarak setelah bagian Total

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.width(180.dp) // Sesuaikan lebar tombol seperti di kode pertama
                    ) {
                        Text(
                            text = "Lihat Ulasan",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LihatUlasanCardPreview() {
    val sampleSelesai = PesananSelesai(
        name = "Ikan Nila",
        weightVariation = "1 Kg",
        price = "50.000",
        quantity = 1,
        imageResId = R.drawable.ikan_nila,
        status = "Pesanan Selesai"
    )

    val navController = rememberNavController()

    LihatUlasanCard(selesai = sampleSelesai, onClick = {
        // Preview click action
    },
        navController = navController)
}
