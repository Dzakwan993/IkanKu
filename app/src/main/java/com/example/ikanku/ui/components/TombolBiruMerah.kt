package com.example.ikanku.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ikanku.ui.screens.TotalSection

@Composable
fun TombolMerahBiru(
    judulBiru: String,
    judulMerah: String,
    onBiruClick: () -> Unit,
    onMerahClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            elevation = ButtonDefaults.buttonElevation(8.dp),
            onClick = onMerahClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3848)),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = judulMerah,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            elevation = ButtonDefaults.buttonElevation(8.dp),
            onClick = onBiruClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = judulBiru, color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddress() {

    TombolMerahBiru(
        judulBiru = "Terima" ,
        judulMerah ="Tolak",
        onMerahClick = {},
        onBiruClick = {}
    )

}