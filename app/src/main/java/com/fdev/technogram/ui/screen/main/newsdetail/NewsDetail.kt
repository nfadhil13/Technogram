package com.fdev.technogram.ui.screen.main.newsdetail

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.outlined.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.onDispose
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.ArticleWebView
import com.fdev.technogram.ui.components.NetworkImage
import com.fdev.technogram.util.DateUtil

@Composable
fun NewsDetail(news: News, modifier: Modifier = Modifier, newsDetailViewModel: NewsDetailViewModel , darkTheme : Boolean) {

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
    ){
        ConstraintLayout(

        ) {

            val (newsItem) = createRefs()

//            IconButton(
//                onClick = {
//                    scrollState.smoothScrollTo(0f)
//                },
//                modifier = Modifier.constrainAs(goTop){
//                    end.linkTo(parent.end)
//                    start.linkTo(parent.start)
//                    bottom.linkTo(parent.bottom, margin = 24.dp)
//                }.background(Color.Red).clip(CircleShape).width(24.dp).height(24 .dp).zIndex(100f)
//            ){
//                Icon(
//                    Icons.Filled.ArrowUpward
//                )
//            }

            ScrollableColumn(
                scrollState = scrollState,
                modifier = modifier.fillMaxWidth().constrainAs(newsItem){
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                }
            ) {
                newsDetailViewModel.news?.let { oldNews ->
                    if (oldNews != news) {
                        newsDetailViewModel.setCurrentNews(news)
                        newsDetailViewModel.onScrollStateChange(0f)
                    } else {
                        println("scroll to ${newsDetailViewModel.scrollState}")
                        scrollState.scrollTo(newsDetailViewModel.scrollState)
                        println("current scroll state ${newsDetailViewModel.scrollState}")
                    }
                } ?: newsDetailViewModel.setCurrentNews(news)
                onDispose(callback = {
                    newsDetailViewModel.onScrollStateChange(scrollState.value)
                })

                if (news.headerImg != "") {
                    NetworkImage(
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        imageUrl = news.headerImg
                    )
                }

                Spacer(modifier = Modifier.height(15.dp).fillMaxWidth())

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .padding(horizontal = 12.dp)
                ) {

                    val (category, time) = createRefs()

                    Card(
                        modifier = Modifier.constrainAs(category) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        },
                        backgroundColor = MaterialTheme.colors.primary,
                        elevation = 0.dp,
                        shape = RoundedCornerShape(2.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = news.category,
                            style = MaterialTheme.typography.caption
                        )

                    }

                    TimeComponent(
                        time = news.publishTime,
                        modifier = Modifier.constrainAs(time) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                    )


                }

                Spacer(modifier = Modifier.height(15.dp).fillMaxWidth())

                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    text = news.title,
                    style = MaterialTheme.typography.h6.merge(TextStyle(fontWeight = FontWeight.W400))
                )

                Spacer(modifier = Modifier.height(15.dp).fillMaxWidth())

                Card(
                    elevation = 3.dp,
                    modifier = Modifier.height(28.dp).fillMaxWidth().padding(horizontal = 12.dp),
                    shape = RoundedCornerShape(2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Create,
                            tint = Color.Gray,
                            modifier = Modifier.fillMaxHeight(0.85f).aspectRatio(1f)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                            Text(
                                text = news.writer,
                                style = MaterialTheme.typography.caption
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(15.dp).fillMaxWidth())
                ArticleWebView(
                    htmlString = news.article,
                    darkTheme = darkTheme,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                        .background(Color.Red)
                )

            }
        }
    }





}

@Composable
fun TimeComponent(time: Long, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = DateUtil.getMonthAndDate(time),
        style = MaterialTheme.typography.caption.merge(TextStyle(color = Color.Gray))
    )
}

@Composable
fun NewsDetailSkeleton() {

}