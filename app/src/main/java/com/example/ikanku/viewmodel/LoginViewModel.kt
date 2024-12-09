package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.network.ApiService
import com.example.ikanku.model.Pembeli
import com.example.ikanku.model.Toko
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: ApiService) : ViewModel() {
    private val _loginStatus = MutableStateFlow<LoginStatus>(LoginStatus.Idle)
    val loginStatus: StateFlow<LoginStatus> = _loginStatus

    fun login(phoneNumber: String, password: String, onSuccess: (Any) -> Unit, onError: (String) -> Unit) {
        _loginStatus.value = LoginStatus.Loading

        viewModelScope.launch {
            try {
                val response = apiService.loginUser(mapOf("no_ponsel" to phoneNumber, "kata_sandi" to password))
                if (response.isSuccessful) {
                    val body = response.body()
                    println("Response Body: $body") // Debugging respons API
                    val userType = body?.userType
                    println("User Type: $userType") // Debugging userType

                    // Tangani "userType" sebagai pembeli jika bernilai "user"
                    when (userType?.lowercase()) {
                        "toko" -> {
                            val toko = body.toko
                            if (toko != null) {
                                _loginStatus.value = LoginStatus.Success(toko)
                                onSuccess(toko)
                            } else {
                                setErrorMessage("Toko tidak ditemukan")
                            }
                        }
                        "pembeli", "user" -> { // Tambahkan "user" di sini
                            val pembeli = body.pembeli
                            if (pembeli != null) {
                                _loginStatus.value = LoginStatus.Success(pembeli)
                                onSuccess(pembeli)
                            } else {
                                setErrorMessage("Pembeli tidak ditemukan")
                            }
                        }
                        else -> {
                            setErrorMessage("Jenis pengguna tidak valid: $userType")
                        }
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Login gagal"
                    setErrorMessage(errorMessage)
                }
            } catch (e: Exception) {
                setErrorMessage("Error: ${e.message}")
            }
        }
    }

    // Fungsi untuk menetapkan pesan kesalahan
    fun setErrorMessage(message: String) {
        _loginStatus.value = LoginStatus.Error(message)
    }

    sealed class LoginStatus {
        object Idle : LoginStatus()
        object Loading : LoginStatus()
        data class Success(val user: Any) : LoginStatus()
        data class Error(val message: String) : LoginStatus()
    }
}
