package com.fdev.technogram.ui.screen.main.searchresult

import androidx.compose.animation.transition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.animations.ColorPulse
import com.fdev.technogram.ui.components.NoMoreNews
import com.fdev.technogram.ui.components.RightImagePreviewNews
import com.fdev.technogram.ui.components.RightImagePreviewNewsSkeleton
import com.fdev.technogram.ui.screen.main.newsdetail.SearchViewType


@Composable
fun Search(
        onNewsClicked: (news: News) -> Unit,
        viewModel: SearchViewModel
) {

    Surface() {
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
                                SearchLoading()
                            }
                        }
                    }
                }
            )
        }
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
            textStyle  = MaterialTheme.typography.caption,
            placeholder = {
                Providers(AmbientContentAlpha provides ContentAlpha.disabled) {
                    Text("Masukan kata kunci disini" , style = MaterialTheme.typography.caption)
                }
            },
            backgroundColor = MaterialTheme.colors.background,
            keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Search),
            onImeActionPerformed = { _, softwareKeyboardController ->
                onSearch()
                softwareKeyboardController?.hideSoftwareKeyboard()
            }

    )
}

@Composable
fun SearchNoItemFound() {
    Text("Penelusuran Tidak Cocok dengan berita manapun")
}

@Composable
fun SearchNoMoreItem() {
    NoMoreNews(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = "Tidak ada lagi berita dengan kata kunci terkait"
    )
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


@Composable
fun SearchLoading(
        modifier: Modifier = Modifier
){
    Column {
        for(i in 1..3){
            SearchLoadingPreview()
        }
    }
}

@Composable
fun SearchLoadingPreview(
        modifier: Modifier = Modifier
){


    val colorPulse = transition(
            definition = ColorPulse.shimmerDefinition,
            initState = ColorPulse.ShimmerState.INITIAL,
            toState = ColorPulse.ShimmerState.FINAL
    )

    val pulseColor = colorPulse[ColorPulse.shimmerKey]

    Column(
            modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        RightImagePreviewNewsSkeleton(skeletonColor = pulseColor)
        Spacer(
                modifier = Modifier
                    .height(height = 1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
        )
    }
}