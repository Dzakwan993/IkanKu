    package com.example.ikanku.ui.screens

    import android.icu.text.CaseMap.Title
    import android.icu.text.IDNA.Info
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.clickable
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
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.SnackbarDefaults.backgroundColor
    import androidx.compose.material.TabRowDefaults.Divider
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Info
    import androidx.compose.material3.AlertDialog
    import androidx.compose.material3.AlertDialogDefaults

    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.RadioButton
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextButton
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.rotate
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.ikanku.R
    import com.example.ikanku.ui.components.CartItem
    import com.example.ikanku.ui.components.CustomTopAppBar
    import com.example.ikanku.ui.components.TombolMerahBiru

    @Composable
    fun HalamanBayar() {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            PaymentMethodDialog(onDismiss = {showDialog = false })
        }
        
        Scaffold(
            topBar = {
                CustomTopAppBar(title = "Ringkasan Pemesanan", onBackClick = {})
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                    ,
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Yellow)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        AddressRow()

                        // Pembatas garis
                        Divider(color = Color(0xFF9095A0), thickness = 1.dp)

                        //Informasi Produk
                        InfoProduk(
                            name = "Bibit nila (Tanpa tulang)",
                            weight = "500g",
                            price = "Rp 50.000",
                            imageRes = android.R.drawable.ic_menu_gallery, // atau resource drawable lain sesuai kebutuhan
                            quantity = 1,
                            onIncrease = { /* Do nothing for preview */ },
                            onDecrease = { /* Do nothing for preview */ }
                        )

                        GabunganTitleKotak(
                            judul = "Catatan untuk Penjual",
                            kotak = {
                                KotakTanpaPanah(
                                    judul = "Opsinal"
                                )
                            }
                        )

                        GabunganTitleKotak(
                            judul = "Metode pembayaran",
                            kotak = {
                                Kotak(
                                    judul = "Pilih Metode Pembayaran"
                                )
                            }
                        )

                        GabunganTitleKotak(
                            judul = "Metode Pengiriman",
                            kotak = {
                                Kotak(
                                    judul = "Pilih Metode Pengiriman"
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    TotalSection(

                        total = "Rp45.000",
                        shippingCost = "Rp5.000"
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                TombolMerahBiru(
                    judulBiru = "Buat Pesanan" ,
                    judulMerah ="Batal"
                )

            }
        }
    }


    @Composable
    fun InfoProduk(
        name: String,
        weight: String,
        price: String,
        imageRes: Int,
        quantity: Int,
        onIncrease: () -> Unit,
        onDecrease: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),


        ) {

            // Product Image
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .border(1.dp, Color.LightGray, shape = CircleShape)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Product Details
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(weight, color = Color.Gray, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(16.dp))
                Text(price, color = Color.Black, fontSize = 16.sp)


            }
        }
    }

    @Composable
    fun GabunganTitleKotak(
        judul: String,
        kotak: @Composable () -> Unit
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Text(
                text = judul
            )
            Spacer(modifier = Modifier.height(8.dp))
            kotak()
        }
    }

        @Composable
        fun Kotak(
            judul: String

        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color(0xFFE8E8E8))

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                        Text(
                            text = judul,
                            fontSize = 15.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.panah_kanan),
                            contentDescription = null
                        )
                }
            }
        }


    @Composable
    fun KotakTanpaPanah(
        judul: String
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()

                .height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color(0xFFE8E8E8))

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = judul,
                    fontSize = 15.sp
                )

            }
        }
    }


    @Composable
    fun AddressRow(
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(horizontal = 8.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.map), // Ganti dengan ikon lokasi yang sesuai
                    contentDescription = "Location Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(15.dp)

                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Alamat",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black,

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Miftahul Fazra (6285274086648)\nPerumahan tiban damai, Blok A\nNo.35, RT.04, RW.07 Kelurahan\nTiban indah, Sekupang",
                        fontSize = 12.sp,
                        color = Color(0xFF171A1F)
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.panah), // Ganti dengan ikon panah yang sesuai
                contentDescription = "Arrow Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(20.dp)
                    .rotate(180f) // Putar 180 derajat untuk menghadap ke kanan
            )
        }
    }

    @Composable
    fun TotalSection(
        modifier: Modifier = Modifier,
        total: String,
        shippingCost: String
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = total,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "* Pengiriman $shippingCost",
                    fontSize = 12.sp,
                    color = Color(0xFFFF4238) // Warna teks merah
                )
            }
        }
    }


    // Dialog untuk metode pembayaran
    @Composable
    fun PaymentMethodDialog(onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = { } ,
            text = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .width(180.dp)
                            .padding(10.dp)
                    ) {
                        Text(text = "biji")
                    }
                }
            },

        )
    }


    @Composable
    fun ShippingMethodDialog(
        onDismiss: () -> Unit,
        selectedOption: String,
        onOptionSelected: (String) -> Unit,
        title: String,
        pilihanSatu: String,
        pilihanDua: String
    ) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = { /* TODO: Implement confirm button action if needed */ },
            title = {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.Gray,

                )
            },
            text = {
                Column {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, end = 8.dp)
                                .clickable {
                                    onOptionSelected("Jemput")
                                    onDismiss()
                                }
                        ) {
                            Text(
                                text = pilihanSatu,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            RadioButton(
                                selected = selectedOption == "Jemput",
                                onClick = {
                                    onOptionSelected("Jemput")
                                    onDismiss()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, end = 8.dp)
                                .clickable {
                                    onOptionSelected("Kirim")
                                    onDismiss()
                                }
                        ) {
                            Text(
                                text = pilihanDua,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            RadioButton(
                                selected = selectedOption == "Kirim",
                                onClick = {
                                    onOptionSelected("Kirim")
                                    onDismiss()
                                }
                            )
                        }
                    }
                }
            },
            containerColor = Color(0xFFE0E0E0) // Warna latar belakang sesuai gambar
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ConfirmationPrompt(
        modifier: Modifier = Modifier,
        title: String,
        message: String,
        onCancel: () -> Unit,
        onConfirm: () -> Unit
    ) {

            AlertDialog(
                modifier = modifier
                    .fillMaxWidth()
                    ,
                onDismissRequest = { onCancel() },
                containerColor = Color.White,
                text = {

                        Column(
                            modifier = Modifier

                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            // Title Text
                            Image(
                                painter = painterResource(R.drawable.search_for_house),
                                contentDescription = null
                            )
                            // Message Text
                            Text(
                                text = message,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                            // Row for Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Cancel Button
                                Button(
                                    onClick = onCancel,
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .padding(end = 4.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3848))
                                ) {
                                    Text("Batal")
                                }
                                // Confirm Button
                                Button(
                                    onClick = onConfirm,
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .padding(start = 4.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD))
                                ) {
                                    Text("Ya")
                                }
                            }
                        }

                },
                confirmButton = {},
                dismissButton = {},



            )
        }




    @Preview(showBackground = true)
    @Composable
    fun PreviewHalmanBayar() {
        HalamanBayar()

    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewAddress() {

    }


