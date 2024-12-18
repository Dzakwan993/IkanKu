package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun CustomTopAppBar(
    title: String,
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(159.dp),
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
        color = Color(0xFF177BCD)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back Icon",
                    tint = Color.White
                )
            }

            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun TopBarHanyaJudul(
    title: String,

) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(159.dp),
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
        color = Color(0xFF177BCD)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun TopBarWithCart(
    title: String,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(159.dp),
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
        color = Color(0xFF177BCD)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFF177BCD))
                .padding(WindowInsets.statusBars.asPaddingValues()),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back Icon",
                    tint = Color.White
                )
            }

            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = { navController.navigate("keranjang_screen") },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.keranjang),
                    contentDescription = "Cart Icon",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TopBarLogin(
    selectedTab: String ,
    onTabSelected: (String) -> Unit = {},
    navController: NavController
) {
    // Menyimpan nilai selectedTab menggunakan remember dan mutableStateOf
//    val selectedTabState = remember { mutableStateOf(selectedTab) }



    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFF177BCD))
            .padding(WindowInsets.statusBars.asPaddingValues()),
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
        color = Color(0xFF177BCD)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_ikanku), // Ganti dengan ikon ikan Anda
                contentDescription = "Logo IkanKu",
                modifier = Modifier.sizeIn(minWidth = 80.dp, maxWidth = 120.dp, minHeight = 80.dp, maxHeight = 120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tab untuk Login dan Daftar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Tombol Login
                TextButton(

                    onClick = {
//                        selectedTabState.value = "Login" // Update

                        onTabSelected("Login")
                        navController.navigate("login_screen")

                        Log.d("TabState", "Tab Selected: Login")

                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (selectedTab == "Login") Color.White else Color.LightGray
                    )
                ) {
                    Text(
                        text = "Login",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

//                Spacer(modifier = Modifier.width(8.dp))

                // Tombol Daftar
                TextButton(
                    onClick = {
//                        selectedTabState.value = "Daftar" // Update selectedTab
                        onTabSelected("Daftar")
                        navController.navigate("register_screen")
                        Log.d("TabState", "Tab Selected: Daftar")
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (selectedTab == "Daftar") Color.White else Color.LightGray
                    )
                ) {
                    Text(
                        text = "Daftar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Indikator tab terpilih
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                if (selectedTab == "Daftar") Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(4.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        )
                )
                if (selectedTab == "Login") Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun TopBarLoginPreview() {
    val navController = rememberNavController() // Create a mock navController
    TopBarLogin(
        selectedTab = "Login",
        onTabSelected = { selectedTab ->
            // You can handle tab selection if needed, or leave it empty
        },
        navController = navController // Pass the mock navController here
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBar() {
    CustomTopAppBar(
        title = "Sample Title",
        onBackClick = { /* Handle back action */ }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarWithCart() {
    TopBarHanyaJudul(
        title = "biji"
    )
}
