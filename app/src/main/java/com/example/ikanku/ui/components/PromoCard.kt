package com.yourpackage.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.R
import com.example.ikanku.ui.theme.IkanKuTheme
import com.yourpackage.ikanku.model.Fish


@Composable
fun PromoCard(discountText: String, imageResId: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(painter = painterResource(id = imageResId), contentDescription = discountText)
            Spacer(modifier = Modifier.width(8.dp))
            Text(discountText, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewBerandaScreen() {
    IkanKuTheme {
        PromoCard(
            discountText = "biji",
            imageResId = R.drawable.ikan
        )
    }
}
