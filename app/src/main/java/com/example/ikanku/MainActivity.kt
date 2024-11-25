package com.example.ikanku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.screens.RejectedOrdersScreen
import com.example.ikanku.ui.theme.IkanKuTheme
import com.example.ikanku.viewmodel.OrderRejectedViewModelDua

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val poppins = FontFamily(
            Font(R.font.poppins_regular, FontWeight.Normal),
            Font(R.font.poppins_bold, FontWeight.Bold)
        )
        enableEdgeToEdge()
        setContent {
            val rejectedOrdersViewModel: OrderRejectedViewModelDua = viewModel()
            RejectedOrdersScreen(viewModel = rejectedOrdersViewModel)
        }
    }
}

@Composable
fun IkanKu() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun IkanKuPreview() {
    IkanKuTheme {
        IkanKu()
    }
}
