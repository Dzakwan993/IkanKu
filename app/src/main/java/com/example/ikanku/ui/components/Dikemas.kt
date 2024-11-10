package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
fun DikemasCard(dikemas: Dikemas) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = dikemas.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(50.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        fontSize = 18.sp,
                        text = dikemas.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        fontSize = 14.sp,
                        text = "Pilih Variasi Berat ${dikemas.weightVariation}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            fontSize = 16.sp,
                            text = "Rp${dikemas.price}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            fontSize = 14.sp,
                            text = "X${dikemas.quantity}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Aksi Batal */ },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Batal", fontSize = 15.sp)
                }
                Button(
                    onClick = { /* Aksi Dikemas */ },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(text = "Dikemas", fontSize = 15.sp, color = Color.White)
                }
            }
        }
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

    DikemasCard(dikemas = sampleDikemas)
}
