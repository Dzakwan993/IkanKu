    package com.example.ikanku.ui.screens

    import TombolMasukkanKeranjang
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
    import androidx.compose.material.ExperimentalMaterialApi
    import androidx.compose.material.ModalBottomSheetLayout
    import androidx.compose.material.ModalBottomSheetValue
    import androidx.compose.material.SnackbarDefaults.backgroundColor
    import androidx.compose.material.TabRowDefaults.Divider
    import androidx.compose.material.TextField
    import androidx.compose.material.TextFieldDefaults
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Info
    import androidx.compose.material.rememberModalBottomSheetState
    import androidx.compose.material3.AlertDialog
    import androidx.compose.material3.AlertDialogDefaults

    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.material3.CardElevation
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
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.rotate
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.example.ikanku.R
    import com.example.ikanku.ui.components.AlertBottomSheet
    import com.example.ikanku.ui.components.CartItem
    import com.example.ikanku.ui.components.CustomTopAppBar
    import com.example.ikanku.ui.components.TombolMerahBiru
    import kotlinx.coroutines.launch

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @Composable
    fun HalamanBayar(navController: NavController) {
        var isBottomSheetVisible by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        var bottomSheetContent by remember { mutableStateOf<@Composable () -> Unit>({}) }

        // State untuk menyimpan pilihan metode pembayaran, pengiriman, dan catatan
        var selectedPaymentMethod by remember { mutableStateOf("Pilih Metode Pembayaran") }
        var selectedShippingMethod by remember { mutableStateOf("Pilih Metode Pengiriman") }
        var noteToSeller by remember { mutableStateOf("Opsional") } // State untuk catatan penjual

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                bottomSheetContent()
            }
        ) {
            Scaffold(
                topBar = {
                    CustomTopAppBar(
                        title = "Ringkasan Pemesanan",
                        onBackClick = { navController.popBackStack() }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 64.dp)
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding( 16.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                AddressRow(
                                    onClick = {
                                        // Ganti dengan navigasi yang sesuai
                                        navController.navigate("alamat")
                                    }
                                )

                                // Pembatas garis
                                Divider(color = Color(0xFF9095A0), thickness = 1.dp)

                                // Informasi Produk
                                InfoProduk(
                                    name = "Ikan Nila",
                                    weight = "500g",
                                    price = "Rp 50.000",
                                    imageRes = android.R.drawable.ic_menu_gallery,
                                    quantity = 1,
                                    onIncrease = { /* Tambahkan aksi penambahan */ },
                                    onDecrease = { /* Tambahkan aksi pengurangan */ }
                                )

                                // Catatan untuk Penjual
                                GabunganTitleKotakTidakWajib(
                                    judul = "Catatan untuk Penjual",
                                    kotak = {
                                        KotakTanpaPanah(
                                            judul = noteToSeller, // Gunakan nilai terbaru dari noteToSeller
                                            onClick = {
                                                coroutineScope.launch {
                                                    bottomSheetContent = {
                                                        CatatanPenjualBottomSheet(
                                                            selectedMethod = noteToSeller, // Kirim nilai saat ini
                                                            onSelectMethod = { updatedNote ->
                                                                noteToSeller = updatedNote // Perbarui nilai catatan
                                                            },
                                                            onDismiss = {
                                                                coroutineScope.launch { bottomSheetState.hide() }
                                                            }
                                                        )
                                                    }
                                                    bottomSheetState.show()
                                                }
                                            }
                                        )
                                    }
                                )


                                // Metode Pembayaran
                                GabunganTitleKotakWajib(
                                    judul = "Metode Pembayaran",
                                    kotak = {
                                        Kotak(
                                            judul = selectedPaymentMethod,
                                            onClick = {
                                                coroutineScope.launch {
                                                    bottomSheetContent = {
                                                        PaymentMethodBottomSheet(
                                                            selectedMethod = selectedPaymentMethod,
                                                            onSelectMethod = {
                                                                selectedPaymentMethod = it
                                                                coroutineScope.launch { bottomSheetState.hide() }
                                                            }
                                                        )
                                                    }
                                                    bottomSheetState.show()
                                                }
                                            }
                                        )
                                    }
                                )

                                // Metode Pengiriman
                                GabunganTitleKotakWajib (
                                    judul = "Metode Pengiriman",
                                    kotak = {
                                        Kotak(
                                            judul = selectedShippingMethod,
                                            onClick = {
                                                coroutineScope.launch {
                                                    bottomSheetContent = {
                                                        ShippingMethodBottomSheet(
                                                            selectedMethod = selectedShippingMethod,
                                                            onSelectMethod = {
                                                                selectedShippingMethod = it
                                                                coroutineScope.launch { bottomSheetState.hide() }
                                                            }
                                                        )
                                                    }
                                                    bottomSheetState.show()
                                                }
                                            }
                                        )
                                    }
                                )
                            }
                        }
//                        Coba tambah sini
                        TombolMerahBiru(
                            judulBiru = "Buat Pesaan",
                            judulMerah = "Batal",
                            onBiruClick = {
                                isBottomSheetVisible = true
//
                            },
                            onMerahClick = {
                                navController.popBackStack()
                            }
                        )
                    }


                    if (isBottomSheetVisible) {
                        AlertBottomSheet(
                            isVisible = isBottomSheetVisible,
                            onDismiss = { isBottomSheetVisible = false },
                            imageResId = R.drawable.peringatan_alamat, // Gambar yang digunakan
                            alertText = "Apakah Alamat Sudah Benar?", // Teks dinamis
                            confirmButtonText = "Ya", // Teks tombol "Ya"
                            cancelButtonText = "Tidak", // Teks tombol "Tidak"
                            onConfirm = {
                                // Logika konfirmasi
                                isBottomSheetVisible = false
                                navController.navigate("transfer_screen")

                            },
                            onCancel = {
                                // Logika batal
                                isBottomSheetVisible = false
                            }
                        )
                    }

                }
            }
        }
    }


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
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Tombol Merah
            Button(
                onClick = onMerahClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.weight(1f).padding(end = 4.dp)
            ) {
                Text(text = judulMerah, color = Color.White, fontWeight = FontWeight.Bold)
            }

            // Tombol Biru
            Button(
                onClick = onBiruClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.weight(1f).padding(start = 4.dp)
            ) {
                Text(text = judulBiru, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
    @Composable
    fun CatatanPenjualBottomSheet(
        selectedMethod: String, // Awalnya gunakan nilai `noteToSeller`
        onSelectMethod: (String) -> Unit, // Callback untuk mengirim teks
        onDismiss: () -> Unit // Callback untuk menutup BottomSheet
    ) {
        val note = remember { mutableStateOf("") } // Gunakan `selectedMethod` sebagai nilai awal

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Catatan untuk Penjual",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 8.dp)
            )

            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp)
            ) {
                TextField(
                    value = note.value,
                    onValueChange = { note.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    placeholder = { Text(
                        "Masukkan Catatan Untuk Penjual",
                        color = Color(0xFFB2B2B2)
                    ) },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        backgroundColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }

            TombolMasukkanKeranjang(
                text = "Kirim",
                onClick = {
                    onSelectMethod(note.value) // Kirim teks ke UI utama
                    onDismiss() // Tutup BottomSheet
                }
            )
        }
    }


    @Composable
    fun PaymentMethodBottomSheet(
        selectedMethod: String,
        onSelectMethod: (String) -> Unit
    ) {
        var selectedOption by remember { mutableStateOf(selectedMethod) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Pilih Metode Pembayaran",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            PaymentMethodItem(
                title = "Transfer Bank",
                selected = selectedOption == "Transfer Bank",
                onSelect = { onSelectMethod("Transfer Bank") }
            )

            PaymentMethodItem(
                title = "Bayar di Tempat",
                selected = selectedOption == "Bayar di Tempat",
                onSelect = { onSelectMethod("Bayar di Tempat") }
            )
        }
    }

    @Composable
    fun ShippingMethodBottomSheet(
        selectedMethod: String,
        onSelectMethod: (String) -> Unit
    ) {
        var selectedOption by remember { mutableStateOf(selectedMethod) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Pilih Metode Pengiriman",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            ShippingMethodItem(
                title = "Jemput di Tempat",
                selected = selectedOption == "Jemput di Tempat",
                onSelect = { onSelectMethod("Jemput di Tempat") }
            )
            ShippingMethodItem(
                title = "Kirim ke Rumah",
                selected = selectedOption == "Kirim ke Rumah",
                onSelect = { onSelectMethod("Kirim ke Rumah") }
            )
        }
    }

    @Composable
    fun PaymentMethodItem(title: String, selected: Boolean, onSelect: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(onClick = onSelect),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            RadioButton(
                selected = selected,
                onClick = onSelect
            )
        }
    }

    @Composable
    fun ShippingMethodItem(title: String, selected: Boolean, onSelect: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(onClick = onSelect),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            RadioButton(
                selected = selected,
                onClick = onSelect
            )
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
            // Gambar Produk
            Image(
                painter = painterResource(id = R.drawable.ikan_nila),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .border(1.dp, Color.LightGray, shape = CircleShape)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // Detail Produk
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(weight, color = Color.Gray, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(price, color = Color.Black, fontSize = 16.sp)

                    // Menampilkan jumlah pesanan
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "x$quantity",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                    }
                }
            }
        }
    }

    @Composable
    fun GabunganTitleKotakWajib(
        judul: String,
        kotak: @Composable () -> Unit
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Row {
                Text(
                text = judul
            )
                Text(
                    color = Color(0xFFFF4238),
                    text = "*"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            kotak()
        }
    }

@Composable
    fun GabunganTitleKotakTidakWajib(
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
        judul: String,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color(0xFFE8E8E8))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = Color(0xFFB2B2B2),
                    text = judul,
                    fontSize = 15.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.panah_kanan), // Ikon panah kanan
                    contentDescription = null
                )
            }
        }
    }


    @Composable
    fun KotakTanpaPanah(
        judul: String,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
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
                    color = Color(0xFFB2B2B2),
                    text = judul,
                    fontSize = 15.sp
                )

            }
        }
    }


    @Composable
    fun AddressRow(
        modifier: Modifier = Modifier,
        onClick: () -> Unit  // Menambahkan parameter onClick
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(horizontal = 8.dp)
                .clickable(onClick = onClick),

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
                painter = painterResource(id = R.drawable.lihat_detail), // Ganti dengan ikon panah yang sesuai
                contentDescription = "Arrow Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(20.dp)
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
        val navController = rememberNavController()
        HalamanBayar(navController = navController)

    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewAddress() {
        CatatanPenjualBottomSheet(
            selectedMethod = "Makan",
            onSelectMethod = {},
            onDismiss = {}
        )
    }


