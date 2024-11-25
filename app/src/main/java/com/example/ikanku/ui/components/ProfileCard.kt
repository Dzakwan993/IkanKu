package com.example.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Profile
import com.example.ikanku.viewmodel.ProfileViewModel

@Composable
fun ProfileCard(profile: Profile, modifier: Modifier = Modifier, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = profile.imageRes),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = profile.name, fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate("data_profile")}) {
                Icon(
                    painter = painterResource(id = R.drawable.pensil),
                    contentDescription = "Settings Icon",
                    tint = Color.Gray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    // Initialize the ProfileViewModel
    val profileViewModel: ProfileViewModel = viewModel()

    // Access the profile from the ViewModel
    val profile = profileViewModel.profile

    val navController = rememberNavController()

    // Display the ProfileCard with data from the ViewModel
    ProfileCard(profile = profile, navController = navController)
}
