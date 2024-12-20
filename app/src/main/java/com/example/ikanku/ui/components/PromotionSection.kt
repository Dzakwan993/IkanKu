import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.model.Promotion
import com.example.ikanku.ui.components.PromotionCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun PromotionSection(promotions: List<Promotion>, navController: NavController) {
    // Use accompanist pager's rememberPagerState
    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000) // Delay between pages
            val nextPage = (pagerState.currentPage + 1) % promotions.size
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(durationMillis = 600)
            )
        }
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Promosi Spesial",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        HorizontalPager(
            count = promotions.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            val promotion = promotions[page]
            PromotionCard(
                imageRes = promotion.imageRes,
                title = promotion.title,
                discount = promotion.discount,
                navController = navController
            )
        }

        // Pager indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            activeColor = Color(0xFF9095A0),
            inactiveColor = Color(0xFFDEE1E6)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPromotionSection() {
    val dummyPromotions = listOf(
        Promotion(
            imageRes = R.drawable.ic_launcher_foreground, // Ganti dengan resource gambar
            title = "Diskon Spesial 50%",
            discount = "Hanya untuk hari ini!"
        ),
        Promotion(
            imageRes = R.drawable.ic_launcher_foreground, // Ganti dengan resource gambar
            title = "Gratis Ongkir",
            discount = "Untuk semua pembelian!"
        ),
        Promotion(
            imageRes = R.drawable.ic_launcher_foreground, // Ganti dengan resource gambar
            title = "Diskon Tambahan 20%",
            discount = "Khusus pengguna baru!"
        )
    )

    val navController = rememberNavController()

    PromotionSection(promotions = dummyPromotions, navController = navController)
}


