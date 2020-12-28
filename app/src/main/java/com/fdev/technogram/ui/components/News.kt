package com.fdev.technogram.ui.components


import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.typography
import com.fdev.technogram.util.getFullDateFromLong
import com.fdev.technogram.util.getMonthAndDate
import com.fdev.technogram.R
import com.fdev.technogram.ui.gray
import com.fdev.technogram.ui.mainRed


@Composable
fun HeaderNews(news: News, modifier: Modifier = Modifier) {
    Column(
            modifier = modifier
    ) {
        ImageWithErrorImage(
                imageUrl = news.headerImg,
                modifier = Modifier
                        .fillMaxWidth()
                        .weight(7f)
                        .clip(RoundedCornerShape(5.dp))
        )
        Text(

                text = news.title,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                        .weight(3f),
                style = typography.h4
        )
        Row(
                modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
        ) {
            Text(
                    text = "by ${news.writer}",
                    style = typography.h6,
            )
            Text(
                    text = getFullDateFromLong(news.publishTime),
                    style = typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                            .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LeftImageNews(news: News, modifier: Modifier = Modifier) {

    Row(
            modifier = modifier
    ) {
        Card(
                modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .aspectRatio(1f)

        ) {
            ImageWithErrorImage(
                    imageUrl = news.headerImg
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
                modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(3f)
        ) {
            Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = news.title,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.h5.merge(TextStyle(fontWeight = FontWeight.W600))
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "by ${news.writer}",
                    style = typography.h6,
            )
        }

    }

}

@Composable
fun RightImagePreviewNews(news: News, modifier: Modifier = Modifier) {
    Row(
            modifier = modifier.fillMaxWidth().height(180.dp).padding(10.dp)
    ) {
        Column(
                modifier = Modifier.weight(3f)
                        .fillMaxHeight()
        ) {
            Text(
                    text = news.title,
                    style = typography.h4.merge(TextStyle(fontSize = 16.sp)),
                    overflow = TextOverflow.Clip,
                    maxLines = 3,
                    modifier = Modifier.weight(1.5f).fillMaxWidth()
            )
            Row(
                    modifier = Modifier.weight(1.5f).fillMaxWidth()
            ){
                Text(
                        text = news.article,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,

                        style = typography.h5.merge(TextStyle(color = Color.Gray)),
                        modifier = Modifier.weight(1.5f).fillMaxWidth().align(Alignment.CenterVertically)
                )
            }
            Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
            ) {
                Text(
                        text = "by ${news.writer}",
                        modifier = Modifier.weight(1f),
                        style = typography.h6

                )
                Text(
                        text = "| ${news.category}",
                        modifier = Modifier.weight(1f),
                        style = typography.h6.merge(TextStyle(color = Color.Red ))
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
                modifier = Modifier.weight(1.25f)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
        ) {
            ImageWithErrorImage(
                    imageUrl = news.headerImg,
                    modifier = Modifier
                            .weight(3f)
                            .align(Alignment.CenterHorizontally)
                            .aspectRatio(1f)
            )
            Row(
                    modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
            ){
                Text(
                            text = "❤ ${news.likes}",
                            textAlign = TextAlign.Start,
                            style = typography.h6.merge(TextStyle(color = Color.Red)),
                            modifier = Modifier.weight(1f)
                                    .fillMaxHeight()
                    )
                Text(
                        text = getMonthAndDate(news.publishTime),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = typography.h6
                )
            }
        }
    }
}