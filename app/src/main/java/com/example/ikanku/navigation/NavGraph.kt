package com.example.ikanku

import BankManagementScreen
import MetodePembayaranScreen
import MetodePengirimanScreen
import Rekomendasi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument



import com.example.ikanku.ui.screens.AddAddressScreen
import com.example.ikanku.ui.screens.AddressScreen
import com.example.ikanku.ui.screens.AlasanDitolak
import com.example.ikanku.ui.screens.BankAccountScreen
import com.example.ikanku.ui.screens.BerandaScreen
import com.example.ikanku.ui.screens.BiayaOngkosKirimScreen
import com.example.ikanku.ui.screens.ChangeEmailPenjualScreen
import com.example.ikanku.ui.screens.ChangeEmailScreen
import com.example.ikanku.ui.screens.ChangePasswordScreen
import com.example.ikanku.ui.screens.CompleteDataScreen
import com.example.ikanku.ui.screens.ConfirmationScreen
import com.example.ikanku.ui.screens.DataProfileScreen
import com.example.ikanku.ui.screens.DeliveryDetailScreen
import com.example.ikanku.ui.screens.DetailPengiriman
import com.example.ikanku.ui.screens.DetailPesanan
import com.example.ikanku.ui.screens.DetailProduk
import com.example.ikanku.ui.screens.DetailProdukScreen
import com.example.ikanku.ui.screens.DikemasScreen
import com.example.ikanku.ui.screens.DikirimScreen
import com.example.ikanku.ui.screens.DiscountScreen
import com.example.ikanku.ui.screens.EditTokoScreen
import com.example.ikanku.ui.screens.ForgotPasswordScreen
import com.example.ikanku.ui.screens.HalamanBayar
import com.example.ikanku.ui.screens.HargaVariasiScreen
import com.example.ikanku.ui.screens.HelpCenterScreen
import com.example.ikanku.ui.screens.Kategori
import com.example.ikanku.ui.screens.LoginScreen
import com.example.ikanku.ui.screens.OrderScreen
import com.example.ikanku.ui.screens.OrderSummaryScreen
import com.example.ikanku.ui.screens.Pencarian
import com.example.ikanku.ui.screens.PenjualDikemas
import com.example.ikanku.ui.screens.PesananBaru
import com.example.ikanku.ui.screens.ProductScreen
import com.example.ikanku.ui.screens.ProfileScreen
import com.example.ikanku.ui.screens.RegisterScreen
import com.example.ikanku.ui.screens.RejectedOrdersScreen
import com.example.ikanku.ui.screens.ReviewListScreen
import com.example.ikanku.ui.screens.ReviewScreen
import com.example.ikanku.ui.screens.SearchResultScreen
import com.example.ikanku.ui.screens.SelesaiScreen
import com.example.ikanku.ui.screens.SettingsScreen
import com.example.ikanku.ui.screens.ShippingMethodScreen
import com.example.ikanku.ui.screens.ShoppingCartScreenWithCustomAppBar
import com.example.ikanku.ui.screens.SplashScreen
import com.example.ikanku.ui.screens.StartupScreen
import com.example.ikanku.ui.screens.StoreVisitScreen
import com.example.ikanku.ui.screens.TambahKategoriScreen
import com.example.ikanku.ui.screens.TambahProdukScreen
import com.example.ikanku.ui.screens.TokoSayaScreen
import com.example.ikanku.ui.screens.TransferScreen
import com.example.ikanku.ui.screens.UbahNomorPonselScreen
import com.example.ikanku.ui.screens.Ulasan
import com.example.ikanku.ui.screens.VariasiScreen
import com.example.ikanku.ui.screens.VerificationScreen
import com.example.ikanku.ui.screens.VerifyEmailScreen
import com.example.ikanku.viewmodel.RegisterViewModel
import com.example.ikanku.viewmodel.RegisterViewModelFactory

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "daftar_otp"
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
        composable("daftar_otp") {
            ConfirmationScreen(navController = navController)
        }
        composable("daftar_data") {
            val apiService = RetrofitInstance.apiService  // Menggunakan instance dari RetrofitInstance

            val registerViewModel: RegisterViewModel = viewModel(
                factory = RegisterViewModelFactory(apiService)
            )

            CompleteDataScreen(navController = navController, viewModel = registerViewModel)
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
                composable("login_screen") {
                    LoginScreen(navController = navController)
                }
                composable("register_screen") {
                    RegisterScreen(navController = navController)
                }
                composable("daftar_otp") {
                    ConfirmationScreen(navController = navController)
                }
                composable("daftar_data") {
                    val apiService = RetrofitInstance.apiService
                    val registerViewModel: RegisterViewModel = viewModel(
                        factory = RegisterViewModelFactory(apiService)
                    )

                    CompleteDataScreen(navController = navController,viewModel = registerViewModel)
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

                    ShoppingCartScreenWithCustomAppBar(navController = navController) // Show the shopping cart screen

                }
                composable("kategori_screen") {
                    Kategori(navController = navController) // Navigasi ke Kategori Screen
                }
                composable("detail_produk") {
                    DetailProduk(navController = navController)
                }
                composable("daftar_rekomendasi") {
                    Rekomendasi(navController = navController)
                }
                composable("pencarian_screen") {
                    Pencarian(navController = navController) // Navigasikan ke Composable Pencarian
                }
                composable("profile_screen") {
                    ProfileScreen(navController = navController) // Navigasikan ke Composable Pencarian
                }
                composable(
                    route = "search_result_screen?query={query}",
                    arguments = listOf(navArgument("query") { defaultValue = "" })
                ) { backStackEntry ->
                    val query = backStackEntry.arguments?.getString("query") ?: ""
                    SearchResultScreen(navController = navController, query = query)
                }

                composable("halaman_bayar") {
                    HalamanBayar(navController = navController)
                }
                composable("data_profile") {
                    DataProfileScreen(navController = navController)
                }
                composable("pesanan_screen") {
                    OrderScreen(navController = navController)
                }
                composable("dikemas_screen") {
                    DikemasScreen(navController = navController)
                }
                composable("dikirim_screen") {
                    DikirimScreen(
                        onBackClick = { /*TODO*/ },
                        onDeliveryClick = { /*TODO*/ },
                        navController = navController
                    )/* Layar Dikirim */
                }
                composable("selesai_screen") {
                    SelesaiScreen(navController = navController)/* Layar Selesai */
                }
                composable("ditolak_screen") {
        RejectedOrdersScreen(navController = navController)/* Layar Ditolak */
                }
                composable("detail_pengiriman") {
                    DeliveryDetailScreen(onBackClick = { /*TODO*/ }, navController = navController)
                }
                composable("ulasan_saya") {
                    ReviewScreen(onBackClick = { /*TODO*/ }, navController = navController)
                }
                composable("beri_ulasan") {
                    Ulasan(navController = navController)
                }
                composable("alasan_ditolak") {
                    AlasanDitolak(navController = navController)
                }
                composable("change_password_screen") {
                    ChangePasswordScreen(navController = navController)
                }
                composable("ubah_email") {
                    ChangeEmailScreen(navController = navController)
                }
                composable("alamat") {
                    AddressScreen(navController = navController)
                }
                composable("pusat_bantuan") {
                    HelpCenterScreen(navController = navController)
                }
                composable("ubah_emailOTP") {
                    VerifyEmailScreen()
                }
                composable("tambah_alamat") {
                    AddAddressScreen(navController = navController)
                }
                composable("ubah_nomor_ponsel") {
                    UbahNomorPonselScreen(navController = navController) // UbahNomorPonselScreen sudah ada
                }
                composable("ubah_nomor_otp") {
                    VerificationScreen(navController = navController) // UbahNomorPonselScreen sudah ada
                }
                composable("detail_pesanan") {
                    DetailPesanan(navController = navController)
                }
                composable("toko_saya_screen") {
                    TokoSayaScreen(
                        onBackClick = { /*TODO*/ },
                        onCartClick = { /*TODO*/ },
                        navController = navController
                    )
                }





                composable("pesanan_screen_penjual") {
                    PesananBaru(viewModel = viewModel(), navController = navController)
                }
                composable("produk_penjual_screen") {
                    ProductScreen(navController = navController)
                }
                composable("pengaturan_penjual") {
                    SettingsScreen(
                        onBackClick = { navController.popBackStack() },
                        navigateToShippingMethodScreen = {
                            navController.navigate("metode_pengiriman_screen")
                        },
                        navigateToBankAccountScreen = {
                            navController.navigate("bank_management_screen")
                        },
                        navigateToPaymentMethodScreen = {
                            navController.navigate("metode_pembayaran_screen")
                        },
                        navigateToShippingCostScreen = {
                            navController.navigate("biaya_ongkir") // Navigation for Biaya Ongkos Kirim
                        }
                    )
                }

                composable("dikemas_penjual") {
                    PenjualDikemas()
                }
                composable("dikemas_penjual") {
                    PenjualDikemas()
                }
                composable("kunjungi_toko") {
                    StoreVisitScreen(navController = navController)
                }
                composable("transfer_screen") {
                    TransferScreen(navController = navController)
                }
                composable("pesanan_sukses") {
                    OrderSummaryScreen(navController = navController)
                }
                composable("edit_penjual_screen") {
                    EditTokoScreen(
                        onBackClick = { /*TODO*/ },
                        onLogoutClick = { /*TODO*/ },
                        onSaveClick = { /*TODO*/ },
                        onCancelClick = { /* Handle cancel */ },
                        navController = navController
                    )
                }


                composable("metode_pengiriman_screen") {
                    MetodePengirimanScreen(onBackClick = { navController.popBackStack() })
                }
                composable("metode_pembayaran_screen") {
                    MetodePembayaranScreen(onBackClick = { navController.popBackStack() })
                }
                composable("bank_management_screen") {
                    BankManagementScreen(onBackClick = { navController.popBackStack() })
                }
                composable("biaya_ongkir") {
                    BiayaOngkosKirimScreen(
                        onBackClick = { navController.popBackStack() }, // Passing onBackClick lambda
                        navController = navController
                    )
                }

                composable("lupa_sandi_pembeli") {
                    ForgotPasswordScreen(navController = navController)
                }
                composable("ganti_email_penjual") {
                    ChangeEmailPenjualScreen()
                }
                composable("ulasan_produk") {
                    ReviewListScreen(onBackClick = { /*TODO*/ }, navController = navController)
                }
                composable("Detail_produk_penjual") {
                    DetailProdukScreen(navController = navController)
                }
                composable("ulasan_produk_penjual") {
                    ReviewListScreen(onBackClick = { /*TODO*/ }, navController = navController)
                }
                composable("atur_variasi") {
                    VariasiScreen(navController = navController)
                }
                composable("atur_diskon") {
                    DiscountScreen(
                        onBackClick = { /*TODO*/ },
                        onCancelClick = { /*TODO*/ },
                        navController = navController
                    ) {

                    }
                }
                composable("harga_variasi") {
                    HargaVariasiScreen(navController = navController)
                }
                composable("tambah_produk_penjual") {
                    TambahProdukScreen(navController = navController)
                }
                composable("tambah_kategori") {
                    TambahKategoriScreen(navController = navController)
                }



    }
}
