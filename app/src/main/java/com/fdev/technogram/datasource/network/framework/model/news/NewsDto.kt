package com.fdev.technogram.datasource.network.framework.model.news

data class NewsDto(
    val artikel: String = "",
    val createdAt: String = "",
    val deskripsi_jurnalis: String ="",
    val id_admin_pembuat: Int = -1,
    val id_admin_publikasi: Int = -1,
    val id_berita: Int = -1,
    val judul: String = "",
    val jumlah_likes: Int = 0,
    val jumlah_reader: Int = 0,
    val jurnalis: String = "",
    val kategori_berita: String = "",
    val updatedAt: String = "",
    val url_gambar: String = "",
    val waktu_publikasi: String = ""
)