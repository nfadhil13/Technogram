package com.fdev.technogram.util

import com.fdev.technogram.model.News
import java.util.*
import kotlin.collections.ArrayList

fun produceFakeNewsData(id : Int = 5) : News{
    return News(
        id = id,
        title = "Pengguna Brainly Meningkat Selama Pandemi Pengguna Brainly Meningkat Selama Pandemi Pengguna Brainly",
//        headerImg = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Square_-_black_simple.svg/500px-Square_-_black_simple.svg.png",
        headerImg = "https://technogram-api.technogram.tech/app/public/images/8d49115b-ea83-449b-a4e3-a6e65656422a.jpeg",
        article =  "This is the article",
        //2020-12-26T17:55:55.955
        publishTime = Date().time,
        likes = 75,
        reads = 100,
        writer = "Wahyunanda Kusuma Pertiwi"
    )
}

fun produceBunchFakeNews(total : Int) : List<News>{
    val news = ArrayList<News>()
    for (i in 1..total){
        news.add(produceFakeNewsData(i))
    }
    return news
}