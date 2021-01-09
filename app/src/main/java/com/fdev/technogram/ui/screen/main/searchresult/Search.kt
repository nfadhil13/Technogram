package com.fdev.technogram.ui.screen.main.searchresult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.RightImagePreviewNews
import com.fdev.technogram.ui.screen.main.newsdetail.SearchViewType


@Composable
fun Search(
        onNewsClicked: (news: News) -> Unit,
        viewModel: SearchViewModel
) {


    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
    ) {


        SearchSearchBar(
                searchQuery = viewModel.searchQuery,
                onSearchQueryChange = viewModel::changeSearchQuery,
                modifier = Modifier.fillMaxWidth(),
                onSearch = viewModel::search
        )

        Spacer(
                modifier = Modifier.height(20.dp)
        )

        LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    itemsIndexed(items = viewModel.searchViews) { index, item ->

                        if (index == viewModel.searchViews.lastIndex) {
                            viewModel.fetchMore()
                        }
                        when (item) {

                            is SearchViewType.NewsItem -> {
                                SearchResultItem(news = item.news, onNewsClicked = onNewsClicked)
                            }

                            is SearchViewType.NoItemFound -> {
                                SearchNoItemFound()
                            }

                            is SearchViewType.NoMoreItem -> {
                                SearchNoMoreItem()
                            }
                            is SearchViewType.Loading -> {
                                Text("Loading .. ")
                            }
                        }
                    }
                }
        )
    }
}


@Composable
fun SearchSearchBar(
        searchQuery: String,
        onSearchQueryChange: (newSearchQuery: String) -> Unit,
        modifier: Modifier = Modifier,
        onSearch : () -> Unit
) {
    TextField(
            modifier = modifier,
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            trailingIcon = {
                Icon(
                        Icons.Outlined.Search,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.clickable(onClick = onSearch)
                )
            },
            backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun SearchNoItemFound() {
    Text("Penelusuran Tidak Cocok dengan berita manapun")
}

@Composable
fun SearchNoMoreItem() {
    Text("Tidak ada lagi berita dengan kata kunci terkait")
}

@Composable
fun SearchResultItem(
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
        Spacer(
                modifier = Modifier
                        .height(height = 1.dp)
                        .fillMaxWidth()
                        .background(Color.Gray)
        )
    }

}