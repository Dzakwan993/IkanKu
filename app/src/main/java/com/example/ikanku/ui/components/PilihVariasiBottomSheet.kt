package com.example.ikanku.ui.components

import TombolMasukkanKeranjang
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.ui.theme.IkanKuTheme
import org.jetbrains.annotations.Contract

@Composable
fun PilihVariasiBottomSheetContent(
    variations: List<String>,
    onDismiss: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

    ) {
        // Judul
        Text(
            text = "Pilih Variasi",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )

        // Pilihan Variasi
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            variations.forEachIndexed { index, pilihanVariasi ->
                PilihanVariasi(
                    onClick = {},
                    pilihanVariasi = pilihanVariasi,
                    startPadding = if (index == 0) 16.dp else 8.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol masukkan keranjang
        TombolMasukkanKeranjang(
            onClick = onAddToCartClick
        )
    }
}

@Composable
fun PilihanVariasi(
    onClick: () -> Unit,
    pilihanVariasi: String,
    startPadding: Dp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE0E0E0)
        ),
        shape = RoundedCornerShape(13.dp),
        modifier = Modifier.padding(start = startPadding, end = 16.dp)
    ) {
        Text(
            text = pilihanVariasi,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PilihVariasiPreview() {
    IkanKuTheme {
        PilihVariasiBottomSheetContent(
            variations = listOf("200 gr", "250 gr", "300 gr"),
            onAddToCartClick = { /* Tambahkan aksi yang diinginkan, atau kosongkan untuk preview */ },
            onDismiss = { /* Tambahkan aksi yang diinginkan, atau kosongkan untuk preview */ }
        )
    }
}





