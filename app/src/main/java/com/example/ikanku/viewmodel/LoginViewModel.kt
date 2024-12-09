
package com.example.ikanku.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.network.ApiService
import com.example.ikanku.model.Pembeli
import kotlinx.coroutines.launch
import retrofit2.Response
import okhttp3.ResponseBody

class LoginViewModel : ViewModel() {
    private val apiService = RetrofitInstance.apiService

    // Menyimpan status login (berhasil/gagal)
    var loginStatus = mutableStateOf<LoginStatus>(LoginStatus.Idle)

    // Fungsi untuk login
    fun login(phoneNumber: String, password: String) {
        loginStatus.value = LoginStatus.Loading

        viewModelScope.launch {
            try {
                // Mengirim request ke server
                val response = apiService.loginUser(mapOf("no_ponsel" to phoneNumber, "kata_sandi" to password))


                if (response.isSuccessful) {
                    val pembeli = response.body()
                    loginStatus.value = LoginStatus.Success(pembeli)
                } else {
                    loginStatus.value = LoginStatus.Error("Login failed")
                }
            } catch (e: Exception) {
                loginStatus.value = LoginStatus.Error("Error: ${e.message}")
            }
        }
    }

    // Status login
    sealed class LoginStatus {
        object Idle : LoginStatus()
        object Loading : LoginStatus()
        data class Success(val pembeli: Pembeli?) : LoginStatus()
        data class Error(val message: String) : LoginStatus()
    }
}
