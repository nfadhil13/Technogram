package com.fdev.technogram.datasource.network.framework.mapper

import com.fdev.technogram.datasource.DomainMapper
import com.fdev.technogram.datasource.network.NetworkConst
import com.fdev.technogram.datasource.network.framework.model.news.NewsDto
import com.fdev.technogram.model.News
import com.fdev.technogram.util.DateUtil
import com.fdev.technogram.util.htmlToText
import javax.inject.Inject

class NewsNetworkMapper
@Inject
constructor(

) : DomainMapper<NewsDto , News>{


    override fun mapFromDomain(domain: News): NewsDto {
        return NewsDto(

        )
    }

    override fun mapToDomain(t: NewsDto): News {
        return News(
                id = t.id_berita,
                title = t.judul,
                headerImg = NetworkConst.BASE_URL + t.url_gambar,
                article = t.artikel,
                likes = t.jumlah_likes,
                reads = t.jumlah_reader,
                writer = t.jurnalis,
                category = t.kategori_berita,
                publishTime = DateUtil.getDateFromString(t.waktu_publikasi),
                preview = htmlToText(t.artikel , 100)
        )
    }

}