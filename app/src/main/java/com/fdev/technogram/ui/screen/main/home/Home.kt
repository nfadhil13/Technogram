package com.fdev.technogram.ui.screen.main.home


import androidx.compose.animation.transition
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onDispose
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.animations.ColorPulse
import com.fdev.technogram.ui.components.*
import com.fdev.technogram.ui.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


@Composable
fun Home(
    onNewsClicked: (news: News) -> Unit,
) {


    val viewModel: HomeViewModel = viewModel()

    val scrollState = rememberLazyListState(
        initialFirstVisibleItemIndex = viewModel.scrollState.index ,
        initialFirstVisibleItemScrollOffset = viewModel.scrollState.offset
    )


    onDispose(callback = {
        viewModel.setScollState(index = scrollState.firstVisibleItemIndex , offset = scrollState.firstVisibleItemScrollOffset)
    })

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        content = {
            itemsIndexed(
                items = viewModel.homeViewTypes,
            ) { index, item ->
                if (viewModel.shouldFetchMore(index)) {
                    onActive(callback = {
                        viewModel.fetchCurrentNewsNextPage()
                    })
                }
                when (item) {
                    is HomeViewType.Skeleton -> {
                        HomeSkeleton()
                    }
                    is HomeViewType.RecentNews -> {
                        RecentNews(news = item.news, onNewsClicked = onNewsClicked)
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
                                .wrapContentHeight(),
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
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
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
    onNewsClicked: (news: News) -> Unit,
    popularNewsList: List<News>,
    headerNews: News
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
                .heightIn(min = 200.dp, max = 300.dp)
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
                    .heightIn(min = 96.dp, max = 220.dp)
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
        Spacer(modifier = Modifier.height(height = 1.dp).fillMaxWidth().background(Color.Gray))
    }

}

@Composable
fun RecentNews(
    news: News,
    modifier: Modifier = Modifier,
    onNewsClicked: (news: News) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        RightImagePreviewNews(
            news = news,
            modifier = Modifier.clickable(onClick = { onNewsClicked(news) })
        )
        Spacer(modifier = Modifier.height(height = 1.dp).fillMaxWidth().background(Color.Gray))
    }

}


@Composable
fun HomeSkeleton(

) {

    val colorPulse = transition(
        definition = ColorPulse.shimmerDefinition,
        initState = ColorPulse.ShimmerState.INITIAL,
        toState = ColorPulse.ShimmerState.FINAL
    )

    val pulseColor = colorPulse[ColorPulse.shimmerKey]


    Column {
        Box(
            modifier = Modifier.fillMaxWidth(0.45f).height(24.dp).background(pulseColor)
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        HeaderNewsSkeleton(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            skeletonColor = pulseColor

        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        for (i in 1..3) {
            LeftImageNewsSkeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 96.dp, max = 128.dp),

                skeletonColor = pulseColor

            )

            Spacer(modifier = Modifier.height(height = 12.dp))
        }
    }
}

