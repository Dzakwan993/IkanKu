// File: com/example/ikanku/ui/components/DikirimCard.kt

package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.example.ikanku.model.Dikirim

@Composable
fun DikirimCard(
    dikirim: Dikirim,
    onDeliveryClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Tambahkan elevasi untuk shadow
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Product image on the left
            Image(
                painter = painterResource(id = dikirim.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(50.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Text content in a Column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    fontSize = 18.sp,
                    text = dikirim.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    fontSize = 16.sp,
                    text = "Pilih Variasi Berat ${dikirim.weightVariation}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        fontSize = 16.sp,
                        text = "Rp${dikirim.price}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        fontSize = 14.sp,
                        text = "X${dikirim.quantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onDeliveryClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White), // White background
                        modifier = Modifier
                            .border(1.dp, Color.Black, RoundedCornerShape(32.dp)) // Border outline
                            .height(40.dp) // Manual height (adjust as needed)
                            .width(190.dp) // Manual width (adjust as needed)
                            .clip(RoundedCornerShape(32.dp)) // Clip to the same shape as the border
                    ) {
                        Text(
                            fontSize = 16.sp,
                            text = "Proses Pengiriman",
                            color = Color.Black, // Text color black to contrast with white button
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DikirimCardPreview() {
    val sampleDikirim = Dikirim(
        name = "Ikan Nila",
        weightVariation = "1 Kg",
        price = "40.000",
        quantity = 1,
        imageResId = R.drawable.ikan_nila,
        status = "Pesanan Anda sedang dikirim"
    )

    DikirimCard(dikirim = sampleDikirim, onDeliveryClick = {
        // Preview click action
    })
}


