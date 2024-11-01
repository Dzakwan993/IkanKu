import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.ui.components.BottomNavBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BerandaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Set a contrasting background color
    ) {
        HeaderSection()
        SearchBar()
        CategorySection()
        PromotionSection()
        RecommendationSection()
        Spacer(modifier = Modifier.height(16.dp)) // Space before BottomNavBar
        BottomNavBar()
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray), // Add a background color for visibility
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hallo, fazra!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* Cart Action */ }) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Cart", tint = Color.White)
        }
    }
}


@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Telusuri") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

@Composable
fun CategorySection() {
    Text(
        text = "Semua kategori",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
    LazyRow {
        items(3) {
            CategoryCard("Hias") // Change as needed
        }
    }
}

@Composable
fun CategoryCard(categoryName: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Info, // Placeholder icon
                contentDescription = categoryName,
                modifier = Modifier.size(50.dp)
            )
            Text(text = categoryName, fontSize = 14.sp)
        }
    }
}

@Composable
fun PromotionSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.Info, // Placeholder icon for promotion image
                contentDescription = "Promotion",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(text = "Ikan Mas", fontSize = 18.sp, color = Color.White)
                Text(text = "Diskon 50%", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Button(
                    onClick = { /* Buy Now Action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Beli Sekarang", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun RecommendationSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Rekomendasi untuk anda", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        TextButton(onClick = { /* Lihat Semua Action */ }) {
            Text("Lihat semua", color = Color.Blue)
        }
    }
    LazyColumn {
        items(5) { // Sample recommendation items
            FishItem("Ikan Blue Devil", "Rp. 500.000")
        }
    }
}

@Composable
fun FishItem(name: String, price: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Updated elevation
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Info, // Placeholder icon for fish image
                contentDescription = name,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = price, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BerandaScreenPreview() {
    MaterialTheme {
        Surface {
            BerandaScreen()
        }
    }
}

