package com.example.ikanku.model

data class Toko(
    val id_toko: Int,
    val email: String,
    val nama_toko: String,
    val nama_penjual: String,
    val no_ponsel: String,
    val jam_operasional: String,
    val info_pengiriman: String,
    val alamat_toko: String,
    val deskripsi_toko: String,
    val foto_toko: String?,
    val toko_libur: Int,
    val created_at: String,
    val updated_at: String,
    val kata_sandi: String  // Menambahkan kata_sandi untuk autentikasi
)
