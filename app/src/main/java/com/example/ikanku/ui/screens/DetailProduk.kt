package com.example.ikanku.ui.screens


import TombolMasukkanKeranjang
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Divider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.theme.IkanKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailProduk(
    modifier: Modifier = Modifier,
    navController: NavController // Tambahkan NavController untuk navigasi
) {
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            VariasiBottomSheetContent(navController = navController) // Konten Bottom Sheet
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 64.dp) // Memberikan ruang untuk tombol
            ) {
                // TopBar dengan navigasi kembali
                TopBar(
                    modifier = Modifier.padding(bottom = 16.dp),
                    navController = navController // Pastikan ini diteruskan
                )

                // Carousel Gambar
                val fishImages = listOf(
                    R.drawable.ikan, // Gambar di drawable
                    R.drawable.ikan,
                    R.drawable.ikan
                )
                Carousel(images = fishImages)

                // Bagian Harga
                Harga()

                // Deskripsi Produk
                ExpandableDescription()

                // Bagian Toko
                TokoSection(navController = navController)

                // Bagian Review
                ReviewSection(navController =navController)
            }

            // Tombol Masukkan Keranjang
            TombolMasukkanKeranjang(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show() // Menampilkan Bottom Sheet
                    }
                },
                text = "Masukkan Keranjang",
                modifier = Modifier.align(Alignment.BottomCenter) // Posisi tombol di bawah
            )
        }
    }
}


@Composable
fun TombolMasukkanKeranjang(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Blue, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VariasiBottomSheetContent(navController: NavController) {
    val beratOptions = listOf("500 gr", "1 kg", "2 kg", "5 kg")
    val isiOptions = listOf("2 Ekor", "3 Ekor", "4 Ekor", "5 Ekor", "6 Ekor")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Pilih Variasi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Pilihan Berat
        Text(text = "Berat", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(beratOptions) { option ->
                PilihanVariasi(text = option)
            }
        }

        // Pilihan Isi
        Text(text = "Isi", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(isiOptions) { option ->
                PilihanVariasi(text = option)
            }
        }

        // Tombol Masukkan Keranjang
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color(0xFF177BCD), shape = RoundedCornerShape(16.dp))
                .clickable {
                   navController.navigate("keranjang_screen")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Masukkan Keranjang",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
fun PilihanVariasi(text: String) {
    Box(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(Color(0xFFE8E8E8), shape = RoundedCornerShape(8.dp))
            .clickable {
                // Tambahkan aksi untuk memilih variasi
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
    }
}

@Composable
fun ExpandableDescription() {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        // Judul Deskripsi
        Text(
            text = "Deskripsi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Isi Deskripsi
        Text(
            text = if (isExpanded) {
                "Nikmati kesegaran ikan nila terbaik yang langsung didapat dari peternakan berkualitas. "
            } else {
                "Nikmati kesegaran ikan nila terbaik yang langsung didapat dari peternakan berkualitas. Ikan nila segar kami memiliki rasa gurih alami dan daging yang lembut, cocok untuk berbagai olahan seperti ikan bakar, sup, atau pepes. Detail Produk: Berat: ±500 gram - 1 kg per ekor, Kondisi: Segar, belum dibekukan, Asal: Budidaya Tibelat Farm, Manfaat: Kaya akan protein, omega-3, dan rendah lemak, Saran Penyimpanan: Simpan di suhu 0-4°C agar tetap segar."
            },
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Tombol Selengkapnya/Sembunyikan
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color(0xFFE8E8E8), shape = RoundedCornerShape(16.dp))
                .clickable { isExpanded = !isExpanded },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = if (isExpanded) "Selengkapnya" else "Sembunyikan",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(
                        id = if (isExpanded) R.drawable.icon_kebawah else R.drawable.icon_kebawah
                    ), // Ganti dengan resource ikon panah Anda
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp).padding(start = 4.dp)
                )
            }
        }
    }
}


@Composable
fun TokoSection(navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E8E8)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Baris Atas: Tombol "Kunjungi Toko"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f)) // Spacer untuk mendorong tombol ke kanan
                Text(
                    text = "Kunjungi Toko",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("kunjungi_toko")

                        }
                        .border(
                            width = 1.dp,
                            color = Color.Red,
                            shape = RoundedCornerShape(16.dp) // Opsional: Untuk border dengan sudut melengkung
                        )
                        .padding(4.dp) // Tambahkan padding agar teks tidak terlalu menempel pada border
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Baris Tengah: Gambar dan Informasi Toko
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Gambar Toko
                Image(
                    painter = painterResource(id = R.drawable.tibelat_farm), // Ganti dengan ID resource gambar Anda
                    contentDescription = "Logo Toko",
                    modifier = Modifier
                        .size(70.dp) // Ukuran gambar toko lebih kecil
                        .padding(end = 8.dp)
                )

                // Informasi Toko
                Column {
                    Text(
                        text = "Tibelat Farm",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Budidaya Ikan Air Tawar, Restoran, Kolam Pemancingan",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}













@Composable
fun ReviewSection(navController: NavController) {

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
                color = Color(0xFF171A1F),
                modifier = Modifier.clickable {
                    // Handle click action here
                    navController.navigate("ulasan_produk")
                }
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


@Composable
fun Harga() {
    val rating = 4.5 // Rating sebagai nilai desimal

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Harga Produk
        Text(
            text = "Rp30.000",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        // Rating dengan Bintang dan Angka
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700), // Warna bintang kuning
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp)) // Spasi kecil antara bintang dan angka
            Text(
                text = "$rating",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
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
fun TopBar(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.panah), // Ikon panah di drawable
            contentDescription = "Back",
            tint = Color.Black, // Warna ikon
            modifier = Modifier
                .padding(end = 8.dp, start = 16.dp)
                .clickable {
                    navController.popBackStack() // Navigasi kembali ke layar sebelumnya
                }
        )
        Text(
            text = "Ikan Nila",
            modifier = Modifier.weight(1f),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}




@Preview(showBackground = true)
@Composable
fun DetailProdukPreview() {
    val navController = rememberNavController()
    IkanKuTheme {
        DetailProduk( navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewSectionPreview() {
    IkanKuTheme {

    }
}