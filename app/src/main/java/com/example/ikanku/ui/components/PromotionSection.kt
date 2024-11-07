// PromotionSection.kt
package com.example.ikanku.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ikanku.model.Promotion
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromotionSection(promotions: List<Promotion>) {
    // Inisialisasi PagerState dengan jumlah halaman sesuai data
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { promotions.size })

    // Set up auto-scroll effect
    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000) // Mengatur jeda antar halaman (3 detik)
            val nextPage = (pagerState.currentPage + 1) % promotions.size
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(durationMillis = 600) // Durasi animasi 600 ms untuk smooth effect
            )
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Promosi Spesial",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            val promotion = promotions[page]
            PromotionCard(
                imageRes = promotion.imageRes,
                title = promotion.title,
                discount = promotion.discount
            )
        }
    }
}
