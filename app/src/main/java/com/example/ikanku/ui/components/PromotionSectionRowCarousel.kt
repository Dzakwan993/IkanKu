package com.example.ikanku.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ikanku.model.Promotion
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromotionSectionRowCarousel(smallPromotions: List<Promotion>) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { (smallPromotions.size + 1) / 2 })

    LaunchedEffect(pagerState) {
        while (isActive) {  // Menjamin efek hanya aktif saat komposisi berjalan
            delay(3000) // Mengatur jeda antar halaman (3 detik)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(durationMillis = 600) // Durasi animasi 600 ms
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 24.dp)
    ) { page ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val startIndex = page * 2
            val endIndex = (startIndex + 2).coerceAtMost(smallPromotions.size)

            for (index in startIndex until endIndex) {
                val promotion = smallPromotions[index]
                PromotionCardSmall(
                    imageRes = promotion.imageRes,
                    title = promotion.title,
                    discount = promotion.discount,
                    modifier = Modifier.weight(1f)
                )
            }

            if (endIndex - startIndex == 1) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
