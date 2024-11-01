package com.yourpackage.ikanku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource

@Composable
fun CategoryCard(categoryName: String, imageResId: Int) {
    Card(
        modifier = Modifier.size(100.dp, 80.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(painter = painterResource(id = imageResId), contentDescription = categoryName)
            Text(categoryName, fontWeight = FontWeight.Bold)
        }
    }
}
