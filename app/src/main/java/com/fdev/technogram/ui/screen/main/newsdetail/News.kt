package com.fdev.technogram.ui.screen.main.newsdetail

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fdev.technogram.model.News
import com.fdev.technogram.ui.components.ArticleWebView
import com.fdev.technogram.ui.components.NetworkImage
import com.fdev.technogram.ui.mainRed
import com.fdev.technogram.util.DateUtil

@Composable
fun NewsDetail(news: News, modifier: Modifier = Modifier) {
    ScrollableColumn(
        modifier = modifier.fillMaxWidth().fillMaxHeight()
    ) {
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

            val (category , time) = createRefs()

            Card(
                modifier = Modifier.constrainAs(category){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
                backgroundColor = Color.Red,
                elevation = 0.dp,
                shape = RoundedCornerShape(2.dp)
            ){
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = news.category,
                        style = MaterialTheme.typography.caption.merge(TextStyle(color = Color.White))
                    )

            }

            TimeComponent(
                time = news.publishTime,
                modifier = Modifier.constrainAs(time){
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
        ){
            Row(
                modifier = Modifier.padding(5.dp)
            ){
                Icon(
                  Icons.Outlined.Create,
                    tint = Color.Gray,
                    modifier = Modifier.fillMaxHeight(0.85f).aspectRatio(1f)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = news.writer,
                    style = MaterialTheme.typography.caption.merge(TextStyle(color = Color.Gray))
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp).fillMaxWidth())
        ArticleWebView(
            htmlString = news.article,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 6.dp)
        )
    }

}

@Composable
fun TimeComponent(time : Long , modifier: Modifier = Modifier){
    Text(
        modifier = modifier,
        text = DateUtil.getMonthAndDate(time),
        style = MaterialTheme.typography.caption.merge(TextStyle(color = Color.Gray))
    )
}