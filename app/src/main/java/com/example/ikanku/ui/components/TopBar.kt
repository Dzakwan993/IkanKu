import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(Color(0xFF177BCD))
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hallo, Naufal!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate("keranjang_screen") }) {
                Icon(
                    painter = painterResource(id = R.drawable.keranjang),
                    contentDescription = "Cart",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .clickable { navController.navigate("pencarian_screen") }
                .background(Color.White)
                .padding(16.dp), // Menambahkan padding untuk klik area
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cari),
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Telusuri",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackIcon(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(Color(0xFF177BCD))

            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(bottom = 24.dp, top = 16.dp, end = 24.dp)

    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = { navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            TextField(

                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Telusuri", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cari),
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    ,

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )


            )
            
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(Color(0xFF177BCD))

            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp, top = 16.dp)

    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically

        ) {

            TextField(

                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Telusuri", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cari),
                        contentDescription = "Search Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate("search_result_screen")
                            }

                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()

                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                ,

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )


            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    val navController = rememberNavController()
    TopBar(navController)
}


@Preview(showBackground = true)
@Composable
fun TopBarWithBackIconPreview() {
    val navController = rememberNavController()
    TopBarSearch(navController)
}

