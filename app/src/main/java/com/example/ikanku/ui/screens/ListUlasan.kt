package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Import Color class
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.BottomNavBarPenjual
import com.example.ikanku.ui.components.TopBarWithCart
import com.example.ikanku.ui.components.CardUlasanList // Import CardUlasanList
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun ReviewListScreen(
    onBackClick: () -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = {
            // TopBar without cart button
            CustomTopAppBar(title = "Ulasan Produk", onBackClick = {navController.popBackStack()})
        },

                bottomBar = {
            // Bottom Navigation Bar (Penjual)
            BottomNavBar(navController = navController)
        },
        content = { paddingValues ->
            // Main content of the screen (will respect the padding)
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {

                // Rating Breakdown Section
                Spacer(modifier = Modifier.height(16.dp))
                RatingBreakdownScreen() // Showing the rating breakdown

                // Divider to separate sections, color changed to gray
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    color = Color.Gray, // Set the divider color to gray
                    thickness = 2.dp // Set thickness to 2dp
                )

                // "All Reviews" section with filter icon
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Space between the text and the icon
                ) {
                    Text(
                        text = "Semua Ulasan",
                        style = MaterialTheme.typography.titleLarge, // Use titleLarge style
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // Using a drawable resource for the filter icon
                    IconButton(onClick = { /* Handle filter click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter), // Replace 'filter' with your actual drawable resource name
                            contentDescription = "Filter",
                            tint = MaterialTheme.colorScheme.primary // Color of the icon
                        )
                    }
                }

                // Call CardUlasanList to display reviews
                CardUlasanList() // This will display the list of reviews
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewReviewListScreen() {
    val navController = rememberNavController()
    ReviewListScreen(
        onBackClick = { /* Handle back click */ },
        navController = navController
    )
}
