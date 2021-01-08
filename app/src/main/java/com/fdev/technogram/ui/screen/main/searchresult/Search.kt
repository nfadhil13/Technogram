package com.fdev.technogram.ui.screen.main.searchresult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.RightImagePreviewNews





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
        Spacer(
                modifier = Modifier
                        .height(height = 1.dp)
                        .fillMaxWidth()
                        .background(Color.Gray)
        )
    }

}