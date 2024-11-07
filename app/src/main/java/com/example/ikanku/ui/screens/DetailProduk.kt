package com.example.ikanku.ui.screens


import TombolMasukkanKeranjang
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Divider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.R
import com.example.ikanku.ui.theme.IkanKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator


@Composable
fun DetailProduk(
    modifier: Modifier = Modifier,
    onAddToCartClick: () -> Unit // Menambahkan parameter untuk aksi tombol
) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 64.dp)
        ) {
            TopBar(
                modifier = Modifier.padding(bottom = 32.dp)
            )
            val fishImages = listOf(
                R.drawable.ikan, // Pastikan nama ini sesuai dengan nama gambar di drawable
                R.drawable.ikan,
                R.drawable.ikan
            )
            Carousel(images = fishImages)
            Harga()
            DeskripsiProduk()
            TextUlasan()

            ReviewSection()
        }

        TombolMasukkanKeranjang(
            onClick = {
//                coroutineScope.launch { sheetState.show() } // Menampilkan bottom sheet saat tombol diklik
            },
            modifier = Modifier.align(Alignment.BottomCenter) // Menempatkan tombol di bagian bawah dalam Box
        )
    }

    if(showDialog) {
//        panggil fungsi di sini

    }


}





@Composable
fun ReviewSection() {

    val rating = 4
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Penilaian & Ulasan Produk",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Lihat Semua",
                fontSize = 14.sp,
                color = Color(0xFF171A1F)
            )

        }

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8E8E8)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),

                ) {
                    Text(text = "4.5/5", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "(50 Ulasan)", fontSize = 12.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row( verticalAlignment = Alignment.CenterVertically ) {
                        repeat(5) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if(index < rating) Color(0xFFFFD700) else Color(0xFFDEE1E6),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

//            Bar rating
                Column(
                    modifier = Modifier.weight(1F),
                    horizontalAlignment = Alignment.End
                ) {
                    for (i in 5 downTo 1) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                progress = when (i) {
                                    5 -> 0.4f
                                    4 -> 0.6f
                                    3 -> 0.4f
                                    2 -> 0.2f
                                    else -> 0.1f
                                },
                                modifier = Modifier
                                    .height(8.dp)
                                    .width(130.dp)
                                    .padding(end = 8.dp),
                                color = Color.Black
                            )
                            Text(
                                text = "$i",
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))

                    }

                }
            }
        }

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 4.dp
                )
        )
        // Ulasan Pengguna
        UserReviewItem(
            name = "Mutu",
            review = "Solusi yg tepat kalo lagi males belanja ke pasar, pengemasannya juga aman lorem lorem lorem lorem lorem lorem lorem   "
        )
        UserReviewItem(
            name = "Kak Ros",
            review = "Produk sangat baik harga terjangkau, kualitas dan respon admin cepat."
        )
    }
}


@Composable
fun UserReviewItem(name: String, review: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E8E8)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Foto Profil
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Column untuk Nama dan Review
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Row untuk Nama dan Rating di bagian atas
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color(0xFFFFB91B),
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }

                // Teks Review, sejajar dengan Nama
                Text(
                    text = review,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun Harga(
) {
    val rating = 4

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rp30.000",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

            Row( verticalAlignment = Alignment.CenterVertically ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if(index < rating) Color(0xFFFFD700) else Color(0xFFDEE1E6),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

        }


}


@Composable
fun DeskripsiProduk() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Deskripsi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        // Isi Deskripsi
        Text(
            text = "Nikmati kesegaran ikan nila terbaik yang langsung didapat dari peternakan berkualitas. Ikan nila segar kami memiliki rasa gurih alami dan daging yang lembut, cocok untuk berbagai olahan seperti ikan bakar, sup, atau pepes.",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Judul Detail Produk
        Text(
            text = "Detail Produk:",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Detail Produk dengan List
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            DetailItem(text = "Berat: ±500 gram - 1 kg per ekor")
            DetailItem(text = "Kondisi: Segar, belum dibekukan")
            DetailItem(text = "Asal: Budidaya Tibelat Farm")
            DetailItem(text = "Manfaat: Kaya akan protein, omega-3, dan rendah lemak")
            DetailItem(text = "Saran Penyimpanan: Simpan di suhu 0-4°C agar tetap segar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Pesan tambahan
        Text(
            text = "Pesan sekarang dan pastikan ikan nila segar hadir di meja makan Anda!",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TextUlasan() {

}

@Composable
fun DetailItem(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = "•",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(
    images: List<Int>
) {
    val pageState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            count = images.size,
            state = pageState,

            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
        ) {
            page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            )

        }

        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .padding(16.dp),
            activeColor = Color(0xFF9095A0),    // Warna aktif
            inactiveColor = Color(0xFFDEE1E6)
        )
    }

}


@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.panah), // Ganti dengan nama ikon di drawable
            contentDescription = null,
            tint = Color.Black,// Pilih warna sesuai kebutuhan atau biarkan aslinya,
            modifier = Modifier.padding(
                end = 8.dp,
                start = 16.dp
            )
        )
        Text(
            text = "Ikan Nila",
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.keranjang), // Ganti dengan nama ikon di drawable
            contentDescription = null,
            tint = Color(0xFF2541D0),
            modifier = Modifier.padding(end = 16.dp)

        )
    }

}


@Preview(showBackground = true)
@Composable
fun DetailProdukPreview() {
    IkanKuTheme {
        DetailProduk(onAddToCartClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewSectionPreview() {
    IkanKuTheme {

    }
}