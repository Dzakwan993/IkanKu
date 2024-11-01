package com.example.ikanku.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton // Tambahkan ini untuk membuat ikon dapat diklik
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.ikanku.R

@Composable
fun CustomTopAppBar(
    title: String,
    onBackClick: () -> Unit // Tambahkan parameter untuk meng-handle klik
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(159.dp),
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
        color = Color(0xFF177BCD) // Mengubah warna latar belakang Surface
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Menambahkan ikon di sebelah kiri teks
            IconButton(
                onClick = onBackClick, // Menambahkan aksi saat diklik
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp) // Padding untuk memberikan jarak
                    .size(24.dp) // Sesuaikan ukuran ikon
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back), // Menggunakan ikon dari drawable
                    contentDescription = "Ikon Kembali",
                    tint = Color.White // Warna ikon
                )
            }

            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.White, // Mengubah warna teks agar kontras
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBar() {
    CustomTopAppBar(
        title = "nama_halaman",
        onBackClick = { /* Aksi kembali dapat ditambahkan di sini */ }
    )
}


