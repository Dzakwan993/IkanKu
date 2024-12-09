package com.example.ikanku.model

data class LoginResponse(
    val userType: String,
    val pembeli: Pembeli?,
    val toko: Toko?
)