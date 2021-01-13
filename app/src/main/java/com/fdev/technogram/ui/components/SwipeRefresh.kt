package com.fdev.technogram.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.AndroidView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@Composable
fun SwipeRefreshCompose(
    modifier : Modifier = Modifier,
    isRefreshing : Boolean = false,
    onRefresh : () -> Unit = {},
    isEnable : Boolean = false,
    content : @Composable () -> Unit

){
    val context = AmbientContext.current
    val swipeRefreshLayout =  SwipeRefreshLayout(context)
    val backgroundColor = MaterialTheme.colors.background.toArgb()

    AndroidView({swipeRefreshLayout} , modifier = modifier){
        it.setProgressBackgroundColorSchemeColor(backgroundColor)
        it.setContent(content = content)
        it.isRefreshing = isRefreshing
        it.isEnabled = isEnable
        it.setOnRefreshListener(onRefresh)
    }
}
