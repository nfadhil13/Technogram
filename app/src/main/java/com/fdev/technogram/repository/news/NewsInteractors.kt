package com.fdev.technogram.repository.news

import javax.inject.Inject



class NewsInteractors
@Inject
constructor(
        val fetchRecentNews: FetchRecentNews,
        val fetchMostLikedNews: FethMostLikedNews
)