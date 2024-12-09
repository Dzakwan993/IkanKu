// CategoryCard.kt
package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Category
@Composable
fun CategoryCard(category: Category, navController: NavController) {
    Card(
        modifier = Modifier
            .width(140.dp) // Atur lebar agar lebih kecil
            .height(60.dp) // Tinggi yang lebih pendek
            .padding(4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = {
            // Navigasi ke halaman Kategori setelah kategori diklik
            navController.navigate("kategori_screen")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            // Gambar kategori
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier
                    .size(40.dp) // Ukuran gambar lebih kecil
                    .clip(RoundedCornerShape(6.dp)) // Bentuk gambar lebih halus
            )

            // Teks kategori
            Text(
                textAlign = TextAlign.Center,
                text = category.name,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis, // Tambahkan "..." jika teks terlalu panjang
                modifier = Modifier.weight(1f) // Biarkan teks mengambil sisa ruang
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCategoryCard() {
    // Dummy data untuk preview
    val category = Category(
        name = "Hias",
        imageRes = R.drawable.kategori_bibit
    )

    val navController = rememberNavController() // Dummy NavController

    // Memanggil fungsi CategorySection dengan data dummy
    CategoryCard(category = category, navController = navController)
}
