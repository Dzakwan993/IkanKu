package com.example.ikanku.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.model.Kecamatan
import com.example.ikanku.model.KodePos
import com.example.ikanku.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _kecamatanList = MutableStateFlow<List<Kecamatan>>(emptyList())
    val kecamatanList: StateFlow<List<Kecamatan>> get() = _kecamatanList

    private val _kodePosList = MutableStateFlow<List<KodePos>>(emptyList())
    val kodePosList: StateFlow<List<KodePos>> get() = _kodePosList

    // Ambil data kecamatan
    fun fetchKecamatan() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getKecamatan()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _kecamatanList.value = it
                    }
                } else {
                    _errorMessage.value = "Gagal mengambil data kecamatan"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Terjadi kesalahan: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Ambil data kode pos berdasarkan kecamatan
    fun fetchPostalCodes(districtId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = apiService.getKodePosByKecamatan(districtId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _kodePosList.value = it
                    }
                } else {
                    _errorMessage.value = "Gagal mengambil data kode pos"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Terjadi kesalahan: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Fungsi untuk registrasi pengguna
    fun registerUser(
        fullName: String,
        email: String,
        address: String,
        districtId: String,
        postalCodeId: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val firebaseUser = FirebaseAuth.getInstance().currentUser
                val uid = firebaseUser?.uid
                val phone = firebaseUser?.phoneNumber
                val token = firebaseUser?.getIdToken(true)?.await()?.token

                if (uid.isNullOrEmpty() || phone.isNullOrEmpty() || token.isNullOrEmpty()) {
                    Log.e("RegisterViewModel", "UID, nomor telepon, atau token tidak ditemukan")
                    onError("UID, nomor telepon, atau token tidak ditemukan.")
                    _isLoading.value = false
                    return@launch
                }

                Log.d("RegisterViewModel", "UID: $uid, Phone: $phone, Token: $token")

                val userData = mapOf(
                    "id_firebase" to uid, // Cocokkan dengan "id_firebase" di backend
                    "nama_lengkap" to fullName, // Cocokkan dengan "nama_lengkap"
                    "no_ponsel" to phone, // Cocokkan dengan "no_ponsel"
                    "email" to email,
                    "detail_alamat" to address, // Cocokkan dengan "detail_alamat"
                    "id_kecamatan" to districtId, // Cocokkan dengan "id_kecamatan"
                    "id_kode_pos" to postalCodeId, // Cocokkan dengan "id_kode_pos"
                    "kata_sandi" to password // Cocokkan dengan "kata_sandi"
                )



                Log.d("RegisterViewModel", "Data yang dikirim ke backend: $userData")

                val response = apiService.registerUser(userData)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    val errorResponse = response.errorBody()?.string()
                    Log.e("RegisterViewModel", "Response error: $errorResponse")
                    onError("Pendaftaran gagal: $errorResponse")
                }
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Terjadi kesalahan: ${e.message}")
                onError("Terjadi kesalahan: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }



}
