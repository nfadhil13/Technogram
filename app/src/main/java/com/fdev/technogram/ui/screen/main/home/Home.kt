package com.fdev.technogram.ui.screen.main.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.HeaderNews
import com.fdev.technogram.ui.components.LeftImageNews
import com.fdev.technogram.ui.components.CircularLoading
import com.fdev.technogram.ui.components.RightImagePreviewNews
import com.fdev.technogram.ui.typography


@Composable
fun Home(
        onNewsClicked: (news : News) -> Unit,
) {


   val viewModel : HomeViewModel = viewModel()

    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        content = {
            itemsIndexed(
                items = viewModel.homeViewTypes
            ) { index, item ->
                if (viewModel.shouldFetchMore(index)) {
                    onActive(callback = {
                        viewModel.fetchCurrentNewsNextPage()
                    })
                }
                when (item) {
                    is HomeViewType.RecentNews -> {
                        RecentNews(news = item.news , onNewsClicked = onNewsClicked)
                    }
                    is HomeViewType.TopOfHome -> {
                        TopOfHome(
                            onNewsClicked = onNewsClicked,
                            popularNewsList = item.mostLikedNews,
                            headerNews = item.headerNews
                        )
                    }
                    is HomeViewType.NoMoreItem -> {
                        Text(
                            modifier = Modifier.fillMaxWidth()
                                .height(16.dp),
                            text = "No More Item",
                            textAlign = TextAlign.Center,
                            style = typography.h5.merge(
                                TextStyle(
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        )
                    }
                    is HomeViewType.LoadingItem -> {
                        CircularLoading(
                            modifier = Modifier.fillMaxWidth().height(16.dp),
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        })

}




//Views where we put Header News and static 6 popular news
@Composable
fun TopOfHome(
        onNewsClicked: (news : News) -> Unit,
        popularNewsList : List<News> ,
        headerNews : News
) {

    Column {
        Text(
                text = "Popular News",
                style = typography.h2
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        HeaderNews(
                news = headerNews,
                modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable(onClick = ({
                            onNewsClicked(headerNews)
                        }))
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        for (news in popularNewsList) {
            LeftImageNews(
                news = news,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 96.dp, max = 128.dp)
                    .clickable(onClick = {
                        onNewsClicked(news)
                    })
            )

            Spacer(modifier = Modifier.height(height = 12.dp))
        }
        Spacer(modifier = Modifier.height(height = 12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Recent News",
            style = typography.h3,
            textAlign = TextAlign.Start
        )
        Spacer(modifier =  Modifier.height(height = 1.dp).fillMaxWidth().background(Color.Gray))
    }

}

@Composable
fun RecentNews(
    news : News,
    modifier: Modifier = Modifier,
    onNewsClicked: (news: News) -> Unit
){
    Column(
        modifier = modifier
    ){
        Spacer(modifier = Modifier.height(10.dp))
        RightImagePreviewNews(news = news, modifier = Modifier.clickable(onClick = { onNewsClicked(news)}))
        Spacer(modifier =  Modifier.height(height = 1.dp).fillMaxWidth().background(Color.Gray))
    }

}


