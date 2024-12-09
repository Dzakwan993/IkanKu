package com.example.ikanku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ikanku.model.OrederPesananDiterima

@Composable
fun TombolDiCardPesanan(
    modifier: Modifier = Modifier,
    buttonBiruText: String,
    onClickBiru: () -> Unit,
    buttonBiruColor: Color = Color(0xFF177BCD),
    isButtonBiruOutlined: Boolean = false,
    buttonMerahText: String? = null,
    onClickMerah: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Tombol Merah (jika ada)
        if (buttonMerahText != null && onClickMerah != null) {
            Button(
                onClick = onClickMerah,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .height(36.dp)
                    .weight(1f)
            ) {
                Text(buttonMerahText, color = Color.White)
            }
        } else {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Tombol Biru (dapat bergaya outline atau penuh)
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
                    .weight(1f)
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
                    .weight(1f)
            ) {
                Text(buttonBiruText, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TombolDiCardPesananPreview() {
    // Preview dengan data dummy untuk tombol biru dan merah
    TombolDiCardPesanan(
        buttonBiruText = "Ditolak",
        onClickBiru = { /* Handle klik tombol biru */ },
        buttonBiruColor = Color(0xFF177BCD),
        isButtonBiruOutlined = false,
        buttonMerahText = "Merah",
        onClickMerah = { /* Handle klik tombol merah */ }
    )
}

@Preview(showBackground = true)
@Composable
fun TombolDiCardPesananOutlinePreview() {
    // Preview untuk tombol biru dengan outline
    TombolDiCardPesanan(
        buttonBiruText = "Biru Outline",
        onClickBiru = { /* Handle klik tombol biru outline */ },
        buttonBiruColor = Color(0xFF177BCD),
        isButtonBiruOutlined = true,
        buttonMerahText = null,
        onClickMerah = null
    )
}