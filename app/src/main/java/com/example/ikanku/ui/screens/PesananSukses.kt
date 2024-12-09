package com.example.ikanku.ui.screens

import TombolMasukkanKeranjang
import android.icu.text.CaseMap.Title
import android.icu.text.IDNA.Info
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CartItem
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.StarRating
import com.example.ikanku.ui.components.TombolMerahBiru

@Composable
fun OrderSummaryScreen(navController: NavController) {
    var rating by remember { mutableStateOf(0) }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        NewPaymentMethodDialog(onDismiss = {showDialog = false })
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Ringkasan Pemesanan", onBackClick = {navController.popBackStack()})
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(horizontal = 16.dp)
                    .weight(1f)
                    ,
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {


                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(96.dp))
                    Image(

                        painter = painterResource(R.drawable.check_sukses),
                        contentDescription = null,
                        modifier = Modifier.size(72.dp)

                    )

                    Text(
                        text = "Pesanan berhasil dilakukan !",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomButton(
                        text = "Silahkan Cek Pesanan Anda",
                        onClick = {
                            navController.navigate("detail_pesanan")
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))





                }


            }

            Spacer(modifier = Modifier.height(8.dp))
            TombolMasukkanKeranjang(
                onClick = {navController.navigate("beranda_screen")},
                text = "Kembali ke Beranda"
            )
        }
    }
}


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, Color(0xFF177BCD)),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp) // Sesuaikan tinggi tombol
            .padding(horizontal = 16.dp),

        shape = RoundedCornerShape(16.dp), // Sudut membulat

    ) {
        Text(
            text = text,
            color = Color(0xFF177BCD),

        )
    }
}




@Composable
fun NewProductInfo(
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
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .border(1.dp, Color.LightGray, shape = CircleShape)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(weight, color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))
            Text(price, color = Color.Black, fontSize = 16.sp)
        }
    }
}

@Composable
fun CombinedTitleBox(
    title: String,
    boxContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.height(8.dp))
        boxContent()
    }
}

@Composable
fun BoxWithoutArrow(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .border(
                    2.dp,
                    Color(0xFF177BCD),
                    RoundedCornerShape(16.dp)
                ) // Tambahkan border biru dengan ketebalan 2.dp
                .background(Color.White), // Tambahkan latar putih
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = Color.Black // Ubah teks menjadi hitam agar terlihat di latar putih
            )
        }



    }
}


@Composable
fun NewAddressRow(
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
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Location Icon",
                tint = Color.Black,
                modifier = Modifier.size(15.dp)
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
            painter = painterResource(id = R.drawable.panah),
            contentDescription = "Arrow Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
                .rotate(180f)
        )
    }
}


@Composable
fun NewTotalSection(
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
                color = Color(0xFFFF4238)
            )
        }
    }
}


@Composable
fun NewPaymentMethodDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
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
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewOrderSummaryScreen() {
    val navController = rememberNavController()
    OrderSummaryScreen(navController = navController)
}


@Preview(showBackground = true)
@Composable
fun TombolPreview() {
    CustomButton(
        onClick = { /* Navigasi atau aksi yang diinginkan */ },
        text = "Silahkan Cek Pesanan Anda"
    )

}

