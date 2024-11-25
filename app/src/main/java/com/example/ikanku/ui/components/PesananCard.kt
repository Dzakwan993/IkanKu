package com.example.ikanku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderDua
import com.example.ikanku.viewmodel.OrderRejectedViewModel

@Composable
fun PesananCard(
    order: OrderDua,
    buttonBiruText: String,
    onClickBiru: () -> Unit,
    buttonBiruColor: Color = Color(0xFF177BCD), // Warna default biru
    isButtonBiruOutlined: Boolean = false, // Tombol biru bisa outline
    buttonMerahText: String? = null,
    onClickMerah: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ikan_nila),
                contentDescription = order.namaProduk,
                modifier = Modifier
                    .size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = order.namaProduk,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Pilih Variasi Berat 1.5kg",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Rp${order.hargaProduk}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Jika tombol merah tidak ada, tampilkan Spacer untuk mengambil ruang setengah kiri
            if (buttonMerahText != null && onClickMerah != null) {
                Button(
                    onClick = onClickMerah,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .height(36.dp)
                        .weight(1f) // Setengah ruang jika ada tombol merah
                ) {
                    Text(buttonMerahText, color = Color.White)
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .weight(1f) // Spacer mengambil ruang setengah kiri
                        .height(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Tombol biru (dapat bergaya outline atau penuh)
            if (isButtonBiruOutlined) {
                OutlinedButton(
                    onClick = onClickBiru,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(36.dp)
                        .weight(1f) // Tombol biru tetap mengambil setengah kanan
                ) {
                    Text(buttonBiruText, color = Color.Black)
                }
            } else {
                Button(
                    onClick = onClickBiru,
                    colors = ButtonDefaults.buttonColors(containerColor = buttonBiruColor),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .height(36.dp)
                        .weight(1f) // Tombol biru tetap mengambil setengah kanan
                ) {
                    Text(buttonBiruText, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

//@Composable
//@Preview(showBackground = true, )
//fun PreviewOrderCard() {
//    val previewViewModel = OrderRejectedViewModel()
//    val sampleOrder = previewViewModel.rejectedOrders.firstOrNull() ?: return
//    PesananCard(order = sampleOrder,
//        buttonBiruText = "Tampil",
//        onClickBiru = { /* Handle biru button action */ },
//        buttonMerahText = "Batal",
//        onClickMerah = { /* Handle merah button action */ },
//
//    )
//}
