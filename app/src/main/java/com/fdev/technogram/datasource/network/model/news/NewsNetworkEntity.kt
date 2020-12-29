package com.fdev.technogram.datasource.network.model.news

data class NewsNetworkEntity(
    val artikel: String,
    val createdAt: String,
    val deskripsi_jurnalis: String,
    val id_admin_pembuat: Int,
    val id_admin_publikasi: Int,
    val id_berita: Int,
    val judul: String,
    val jumlah_likes: Int,
    val jumlah_reader: Int,
    val jurnalis: String,
    val kategori_berita: String,
    val updatedAt: String,
    val url_gambar: String,
    val waktu_publikasi: String
)