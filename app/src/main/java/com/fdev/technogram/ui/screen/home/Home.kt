package com.fdev.technogram.ui.screen.home


import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.HeaderNews
import com.fdev.technogram.ui.components.LeftImageNews
import com.fdev.technogram.ui.components.RightImagePreviewNews
import com.fdev.technogram.ui.typography
import com.fdev.technogram.util.produceBunchFakeNews
import com.fdev.technogram.util.produceFakeNewsData




@Composable
fun Home(
        onNewsClicked: () -> Unit,
) {


   val viewModel : HomeViewModel = viewModel()

    LazyColumn(
            modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
            content = {
                items(
                        items = viewModel.homeViewTypes
                ) { item ->
                    when (item) {
                        is HomeViewType.RecentNews -> {
                            Spacer(modifier = Modifier.height(10.dp))
                            RightImagePreviewNews(news = item.news)
                        }
                        is HomeViewType.TopOfHome -> {
                            TopOfHome(onNewsClicked = onNewsClicked, popularNewsList = item.mostLikedNews, headerNews = item.headerNews)
                        }
                    }
                }
            })

}





//Views where we put Header News and static 6 popular news
@Composable
fun TopOfHome(
        onNewsClicked: () -> Unit,
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
                        .clickable(onClick = onNewsClicked)
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        for (news in popularNewsList) {
            LeftImageNews(
                    news = news,
                    modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 96.dp, max = 128.dp)
            )

            Spacer(modifier = Modifier.height(height = 12.dp))
        }
    }

}