package com.example.ikanku.model

data class Pembeli(
    val id_pembeli: Int,
    val id_firebase: String,
    val nama_lengkap: String,
    val no_ponsel: String,
    val email: String,
    val kata_sandi: String,
    val detail_alamat: String,
    val id_kecamatan: Int,
    val id_kode_pos: Int
)
