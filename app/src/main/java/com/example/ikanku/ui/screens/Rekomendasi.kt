import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.RecommendationCard
import com.example.ikanku.ui.components.TopBarWithCart
import com.example.ikanku.viewmodel.BerandaViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Rekomendasi(viewModel: BerandaViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopBarWithCart(
                title = "Profile",
                onBackClick = { /* Handle back navigation here */ },
                onCartClick = { /* Handle cart click */ }
            )
        },
        bottomBar = { BottomNavBar() },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Two columns in the grid
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
                verticalArrangement = Arrangement.spacedBy(16.dp)// Adjust padding as needed
            ) {
                items(viewModel.recommendations) { recommendation ->
                    RecommendationCard(recommendation = recommendation)
                }
            }
        }
    )
}

@Preview
@Composable
private fun RekomendasiPreview() {
    Rekomendasi()
}
