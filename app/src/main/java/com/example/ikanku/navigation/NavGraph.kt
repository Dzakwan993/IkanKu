package com.example.ikanku

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ikanku.ui.screens.BerandaScreen
import com.example.ikanku.ui.screens.Kategori
import com.example.ikanku.ui.screens.LoginScreen
import com.example.ikanku.ui.screens.RegisterScreen
import com.example.ikanku.ui.screens.ShoppingCartScreenWithCustomAppBar
import com.example.ikanku.ui.screens.SplashScreen
import com.example.ikanku.ui.screens.StartupScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("login_screen") {
            LoginScreen(navController = navController)
        }
        composable("register_screen") {
            RegisterScreen(navController = navController)
        }
        composable("startup_screen") {
            StartupScreen(
                onLoginClick = {
                    navController.navigate("beranda_screen")
                },
                onWebsiteClick = {
                    // Handle website click here
                }
            )
        }
        composable("beranda_screen") {
            BerandaScreen(navController = navController)
        }
        composable("keranjang_screen") {
            ShoppingCartScreenWithCustomAppBar(
                navController = navController
            ) // Show the shopping cart screen
        }
        composable("kategori_screen") {
            Kategori(navController = navController) // Navigasi ke Kategori Screen
        }
    }
}
