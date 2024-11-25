package com.example.ikanku.model

data class OrderDua(
    val idPesanan: Int, // "id_pesanan" dari API
    val namaProduk: String, // "nama_produk" dari API
    val hargaProduk: Int, // "harga_produk" dari API
    val dekripsiProduk: String?, // "dekripsi_produk" dari API
    val fotoProduk: String?, // "foto_produk" dari API (nullable)
    val kuantitasPembelian: Int // "kuantitas_pembelian" dari API
)
