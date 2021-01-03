package com.fdev.technogram.ui.components


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.typography
import com.fdev.technogram.util.DateUtil


@Composable
fun HeaderNews(news: News, modifier: Modifier = Modifier) {
    Column(
            modifier = modifier
    ) {
        NetworkImage(
                imageUrl = news.headerImg,
                modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(5.dp))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(

                text = news.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                style = typography.h4
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
        ) {
            Text(
                    text = "by ${news.writer}",
                    style = typography.h6,
            )
            Text(
                    text = DateUtil.getFullDateFromLong(news.publishTime),
                    style = typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                            .fillMaxWidth()
            )
        }
    }
}


@Composable
fun HeaderNewsSkeleton(modifier: Modifier = Modifier, skeletonColor: Color = Color.Gray.copy(alpha = 0.6f)) {


    val basicModifier = Modifier.background(skeletonColor)

    Column(
            modifier = modifier
    ) {
        Box(
                modifier = basicModifier
                        .fillMaxWidth()
                        .weight(7f)
                        .clip(RoundedCornerShape(5.dp))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
                modifier = basicModifier
                        .weight(0.75f)
                        .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
                modifier = basicModifier
                        .weight(0.75f)
                        .fillMaxWidth(0.6f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
                modifier = Modifier
                        .weight(0.25f)
                        .fillMaxWidth()
        ) {
            Box(
                    modifier = basicModifier.weight(0.75f).fillMaxHeight()
            )
            Spacer(modifier = Modifier.weight(0.75f))
            Box(
                    modifier = basicModifier.weight(0.5f).fillMaxHeight()
            )
        }
    }
}

@Composable
fun LeftImageNews(news: News, modifier: Modifier = Modifier) {
    val configuration = AmbientConfiguration.current
    Row(
            modifier = modifier
    ) {

        val cardBaseModifier = if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)  Modifier.padding(24.dp) else Modifier

        Card(
                modifier = cardBaseModifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .aspectRatio(1f)

        ) {
            NetworkImage(
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
fun LeftImageNewsSkeleton(modifier: Modifier = Modifier, skeletonColor: Color = Color.Gray.copy(alpha = 0.6f)) {
    Row(
            modifier = modifier
    ) {


        val basicModifier = Modifier.background(color = skeletonColor).clip(RoundedCornerShape(5.dp))

        Card(
                modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .aspectRatio(1f),
                backgroundColor = skeletonColor

        ) {
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
                modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(3f)
        ) {
            Spacer(
                    modifier = basicModifier.fillMaxWidth().fillMaxHeight(0.4f),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Spacer(
                    modifier = basicModifier.fillMaxWidth(0.6f).fillMaxHeight(0.2f),
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
            ) {
                Text(
                        text = news.preview,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,

                        style = typography.h5.merge(TextStyle(color = Color.Gray)),
                        modifier = Modifier.weight(1.5f).fillMaxWidth()
                                .align(Alignment.CenterVertically)
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
                        style = typography.h6.merge(TextStyle(color = Color.Red))
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
                modifier = Modifier.weight(1.25f , fill = false)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
        ) {
            NetworkImage(
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
            ) {
                Text(
                        text = "❤ ${news.likes}",
                        textAlign = TextAlign.Start,
                        style = typography.h6.merge(TextStyle(color = Color.Red)),
                        modifier = Modifier.weight(1f)
                                .fillMaxHeight()
                )
                Text(
                        text = DateUtil.getMonthAndDate(news.publishTime),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = typography.h6
                )
            }
        }
    }
}