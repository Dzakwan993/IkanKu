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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Dikirim
@Composable
fun DikirimCard(
    dikirim: Dikirim,
    onDeliveryClick: () -> Unit,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
                .padding(top = 4.dp)
        ) {
            Image(
                painter = painterResource(id = dikirim.imageResId),
                contentDescription = dikirim.name,
                modifier = Modifier
                    .size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = dikirim.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Pilih Variasi Berat: ${dikirim.weightVariation}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Rp${dikirim.price}",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { navController.navigate("detail_pengiriman") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Detail Pengiriman",
                            color = Color.White
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

    val navController = rememberNavController()

    DikirimCard(dikirim = sampleDikirim, onDeliveryClick = {
        // Preview click action
    },
        navController = navController)
}


